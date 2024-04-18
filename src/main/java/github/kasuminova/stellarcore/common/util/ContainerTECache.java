package github.kasuminova.stellarcore.common.util;

import github.kasuminova.stellarcore.StellarCore;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ContainerTECache {

    private static final Map<Class<? extends AbstractContainerMenu>, Function<AbstractContainerMenu, List<BlockEntity>>> CACHE = new ConcurrentHashMap<>();

    public static List<BlockEntity> getTileEntityList(final AbstractContainerMenu container) {
        Function<AbstractContainerMenu, List<BlockEntity>> func = CACHE.get(container.getClass());
        return func == null ? register(container.getClass()).apply(container) : func.apply(container);
    }

    public static Function<AbstractContainerMenu, List<BlockEntity>> register(final Class<? extends AbstractContainerMenu> cClass) {
        List<Field> availableFields = scanTileEntityField(cClass);
        Function<AbstractContainerMenu, List<BlockEntity>> func = (container) -> {
            if (container == null) {
                return Collections.emptyList();
            }
            return availableFields.stream()
                    .map(field -> safeGetField(container, field, BlockEntity.class))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        };
        CACHE.put(cClass, func);
        return func;
    }

    private static <T> T safeGetField(final Object instance, final Field field, final Class<T> type) {
        Object obj;
        try {
            obj = field.get(instance);
            if (type.isInstance(obj)) {
                return type.cast(obj);
            }
        } catch (Error | Exception e) {
            StellarCore.LOGGER.warn(e);
        }
        return null;
    }

    public static List<Field> scanTileEntityField(final Class<? extends AbstractContainerMenu> containerClass) {
        return scanTileEntityFieldRecursive(containerClass, BlockEntity.class);
    }

    private static List<Field> scanTileEntityFieldRecursive(Class<?> aClass, Class<?> target) {
        List<Field> teFields = new ArrayList<>();

        // 遍历当前类的声明字段
        try {
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                if (target.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    teFields.add(field);
                }
            }
            // 某些模组的黑魔法会导致扫 Field 的时候出现奇怪的问题，特此点名 AE2UEL 的 ContainerCraftConfirm。
        } catch (Error | Exception ignored) {
        }

        // 检查是否有父类，如果有，则递归遍历父类
        try {
            Class<?> superClass = aClass.getSuperclass();
            if (superClass != null && superClass != Object.class) {
                List<Field> parentFields = scanTileEntityFieldRecursive(superClass, target);
                teFields.addAll(parentFields);
            }
        } catch (Error | Exception ignored) {
        }

        return teFields;
    }

}
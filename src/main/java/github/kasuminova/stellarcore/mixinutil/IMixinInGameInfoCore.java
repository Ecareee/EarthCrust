package github.kasuminova.stellarcore.mixinutil;

public interface IMixinInGameInfoCore {

    void addPostDrawAction(Runnable action);

    boolean isPostDrawing();

}
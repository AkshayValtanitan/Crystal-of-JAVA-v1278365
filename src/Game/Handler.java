package Game;

import Inputs.InputMouseListener;
import Inputs.InputKeyboardManager;
import Rendering.Camera;
import Utils.DebugMode;
import Views.ViewManager;
import World.World;

public class Handler {
    private Game game;
    private DebugMode debugMode;
    private ViewManager viewManager;
    private GameState gameState;

    public Handler(Game game) {
        this.game = game;
    }

    public InputKeyboardManager getKeyManager() {
        return game.getKeyManager();
    }

    public InputMouseListener getInputMouseListener() {
        return game.getInputMouseListener();
    }

    public DebugMode getDebugMode() {
        return debugMode;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}

package Rendering;

import Entities.Characters.CharacterManager;
import Entities.Characters.Movement.Movement;
import Game.Handler;
import Utils.DebugMode;
import World.ParseTileTypes;
import World.ParseWorld;
import World.Tile;

import java.awt.*;

public class RenderWorld {
    private final Handler handler;
    private int width, height;
    private int[][][] TileLayers;
    private ParseTileTypes tileTypes;
    private CharacterManager characterManager;
    private Movement movement;

    public RenderWorld(Handler handler, ParseWorld worldParser, CharacterManager characterManager) {

        this.movement = new Movement(handler, worldParser);

        this.movement.setSpawn(300, 300);

        this.handler = handler;
        this.width = worldParser.getWorldWidth();
        this.height = worldParser.getWorldHeight();
        this.TileLayers = worldParser.getLayers();
        this.tileTypes = worldParser.getTileTypes();
        this.characterManager = characterManager;
    }

    public void tick() {
        movement.tick();
    }

    public void render(Graphics g) {
        for (int layer = 0; layer < TileLayers.length; layer++) {
            int xStart = (int) Math.max(0, handler.getGameCamera().getXOffset() / Tile.TILEWIDTH);
            int xEnd = (int) Math.min(width, (handler.getGameCamera().getXOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
            int yStart = (int) Math.max(0, handler.getGameCamera().getYOffset() / Tile.TILEHEIGHT);
            int yEnd = (int) Math.min(height, (handler.getGameCamera().getYOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

            for (int i = yStart; i < yEnd; i++) {
                for (int j = xStart; j < xEnd; j++) {
                    Tile tile = getTile(i, j, layer);
                    if (tile != null) {
                        int tilePosX = (int) (j * Tile.TILEWIDTH - handler.getGameCamera().getXOffset());
                        int tilePosY = (int) (i * Tile.TILEHEIGHT - handler.getGameCamera().getYOffset());

                        tile.render(g, tilePosX, tilePosY);

                        if (DebugMode.debugMode()) {
                            if (layer == DebugMode.getRenderedLayerIndex()) {
                                g.drawRect(tilePosX, tilePosY, Tile.TILEWIDTH, Tile.TILEHEIGHT);
                            }
                        }
                    }
                }
            }

            if (layer == TileLayers.length - 1) {
                movement.render(g);
            }
        }
    }

    public Tile getTile(int x, int y, int layer) {
        if (x < 0 || y < 0 || x >= width || y >= height || layer < 0 || layer >= TileLayers.length) {
            return Tile.defaultTile;
        }
        Tile t = tileTypes.getTile(TileLayers[layer][x][y]);
        if (t == null) {
            return Tile.transparentTile;
        }
        return t;
    }

}
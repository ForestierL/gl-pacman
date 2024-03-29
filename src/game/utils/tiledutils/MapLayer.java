package game.utils.tiledutils;

import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import engine.graphics.GridLayer;
import game.utils.Direction;
import javafx.geometry.Rectangle2D;

public class MapLayer {
    Tileset tileset;
    Long map[][];
    int width, height;
    MapLayer(Long[][] map, int width, int height){
        this.map = map;
        this.width = width;
        this.height = height;
    }

    void setTileset(Tileset tileset) {
        this.tileset = tileset;
    }

    public GridLayer getGridLayer()
    {
        GridLayer gridLayer = new GridLayer();

        int cellsWidth = (int) (tileset.asset.getWidth() / tileset.width);
        int cellsHeight = (int) (tileset.asset.getHeight() / tileset.height);
        int numCells = ((cellsWidth * cellsHeight));
        Rectangle2D[] cellClips = new Rectangle2D[numCells];
        int count = 0;
        for(int i = 0; i < cellsHeight; i++)
        {
            for(int j = 0; j < cellsWidth; j++)
            {
                count++;
                cellClips[i*cellsWidth+j] = new Rectangle2D(j * tileset.width, i * tileset.height, tileset.width, tileset.height);
            }
        }
        System.out.println("Count : " + count);
        System.out.println("Size : " + cellClips.length);

        for(int w = 0; w < width; w++)
        {
            for(int h = 0; h < height; h++)
            {
                if(Math.toIntExact((map[w][h])) < 0)
                {
                    map[w][h] = 59L;
                }
                SpriteTexture spriteTexture = new SpriteTexture(tileset.asset);

                spriteTexture.setViewport(cellClips[Math.toIntExact((map[w][h]))]);

                gridLayer.add(spriteTexture, w, h);
            }
        }
        for(int i = 0; i < width; i++)
        {
            for(int h = 0; h < height; h++)
            {
                System.out.print(map[i][h] + "|");
            }
            System.out.println();
        }

        return gridLayer;
    }
}

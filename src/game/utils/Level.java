package game.utils;

import engine.graphics.GridLayer;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Level
{

    private int width;
    private int height;

    private char[][] terrain;

    // TILESET MANAGEMENT
    private Tileset tileset;

    private ArrayList<Character> terrainTilesIdentifiers = new ArrayList<>();
    private ArrayList<Character> entitiesTilesIdentifiers = new ArrayList<>();
    private Character emptyTileIdentifier;

    public void addTerrainTilesIdentifiers(Character ... identifier)
    {
        terrainTilesIdentifiers.addAll(Arrays.asList(identifier));
    }

    public void addEntityTilesIdentifiers(Character ... identifier)
    {
        entitiesTilesIdentifiers.addAll(Arrays.asList(identifier));
    }

    public void setEmptyTileIdentifier(Character emptyTileIdentifier)
    {
        this.emptyTileIdentifier = emptyTileIdentifier;
    }

    public void setTileset(Tileset tileset)
    {
        this.tileset = tileset;
    }

    public ArrayList<GridLayer> getGridLayers()
    {
        ArrayList<GridLayer> gridLayers = new ArrayList<>();

        gridLayers.add(generateTerrain());
        gridLayers.add(generateEntitiesLayer());

        return gridLayers;
    }

    private GridLayer generateTerrain()
    {
        GridLayer terrainLayer = new GridLayer();

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                ImageView imageView;
                if(terrainTilesIdentifiers.contains(terrain[y][x]))
                {
                    imageView = tileset.getTileImageView(terrain[y][x]);
                }
                else
                {
                    imageView = tileset.getTileImageView(emptyTileIdentifier);
                }
                terrainLayer.add(imageView, x, y);
            }
        }

        return terrainLayer;
    }

    private GridLayer generateEntitiesLayer()
    {
        GridLayer entitiesLayer = new GridLayer();

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                ImageView imageView;
                if(entitiesTilesIdentifiers.contains(terrain[y][x]))
                {
                    imageView = tileset.getTileImageView(terrain[y][x]);
                }
                else
                {
                    imageView = tileset.getTileImageView(emptyTileIdentifier);
                }
                entitiesLayer.add(imageView, x, y);
            }
        }

        return entitiesLayer;
    }


    // LOADING A PLV FILE (Pacman Level)

    public void loadFromPLV(File plvFile) throws IOException
    {
        String data;
        data = new String(Files.readAllBytes(plvFile.toPath()));

        Scanner stringScanner = new Scanner(data);

        int y = 0;

        width = stringScanner.nextInt();
        height = stringScanner.nextInt();

        stringScanner.nextLine();

        terrain = new char[height][width];


        while(stringScanner.hasNextLine() && y < height)
        {
            terrain[y] = rowFromString(stringScanner.nextLine());

            y++;
        }

    }

    public void printTerrain()
    {
        for(int y = 0; y < height; y++)
        {
            System.out.print("• ");
            for(int x = 0; x < width; x++)
            {
                System.out.print(terrain[y][x]);
            }
            System.out.println(" •");
        }
    }

    private char[] rowFromString(String rowAsString)
    {
        char[] row = new char[width];
        int index = 0;
        for(char character : rowAsString.toCharArray())
        {
            row[index] = character;
            index++;
        }
        return row;
    }
}

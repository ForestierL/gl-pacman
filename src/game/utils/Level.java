package game.utils;

import engine.graphics.GridLayer;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Level
{
    private int width, height;

    public char[][] terrain;

    // TILESET MANAGEMENT
    private Tileset tileset;

    public void setTileset(Tileset tileset)
    {
        this.tileset = tileset;
    }

    public ArrayList<GridLayer> getGridLayers()
    {
        ArrayList<GridLayer> gridLayers = new ArrayList<>();

        gridLayers.add(generateBackgroundLayer());

        return gridLayers;
    }

    private GridLayer generateBackgroundLayer()
    {
        GridLayer terrainLayer = new GridLayer();

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                ImageView imageView = tileset.getTileImageView('0');
                terrainLayer.add(imageView, x, y);
            }
        }

        return terrainLayer;
    }


    // LOADING A PLV FILE (Pacman Level)

    public void loadFromPLV(File plvFile)
    {
        try
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
        } catch (IOException e)
        {
            e.printStackTrace();
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

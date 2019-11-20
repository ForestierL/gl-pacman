package game.utils;

import engine.ui.GridLayer;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class Level
{

    private ArrayList<MapLayer> mapLayers = new ArrayList<>();
    private int width, height;
    private int tileWidth, tileHeight;

    public Level() {
    }

    public void addTileset(String path, int layerType) {
        File file = new File(path);
        Image asset = new Image(file.toURI().toString());
        mapLayers.get(layerType).setTileset(
                new Tileset(asset, tileWidth, tileHeight)
        );
    }

    private Long[][] array1Dto2D(ArrayList<Long> array, int width, int height) {
        Long[][] map = new Long[width][height];
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
                map[i][j] = array.get((j * width) + i)-1;
            return map;
    }

    public void loadFromJson(String file)
    {

        Object obj = null;
        try
        {

            obj = new JSONParser().parse(new FileReader(file));

        } catch (ParseException | IOException e)
        {
            e.printStackTrace();
        }
        JSONObject mainJSON = (JSONObject) obj;

        if (mainJSON == null) return;

        tileHeight = (int)(long) mainJSON.get("tileheight");

        tileWidth = (int)(long) mainJSON.get("tilewidth");

        width = (int)(long) mainJSON.get("width");
        height = (int)(long) mainJSON.get("height");

        JSONArray layers = (JSONArray) mainJSON.get("layers");

        for (Object o : layers)
        {
            JSONObject layer = (JSONObject) o;
            JSONArray jsonMap = (JSONArray) layer.get("data");
            this.mapLayers.add(new MapLayer(
                    array1Dto2D(jsonMap, width, height),
                    width,
                    height));
        }

        JSONArray tilesets = (JSONArray) mainJSON.get("tilesets");

        for(Object object : tilesets)
        {
            JSONObject tileset = (JSONObject) object;
            if(tileset.get("name").equals("tileset"))
            {
                Tileset myTileset = new Tileset(new Image((String)tileset.get("image")), tileWidth, tileHeight);
                for(MapLayer mapLayer : mapLayers) mapLayer.setTileset(myTileset);
            }
        }
    }

    public ArrayList<GridLayer> getGridLayers()
    {
        ArrayList<GridLayer> gridLayers = new ArrayList<>();
        int kount = 0;
        for(MapLayer mapLayer : mapLayers) {
            System.out.println("LAYER KOUNT : " + kount++);
            gridLayers.add(mapLayer.getGridLayer());
        }
        return gridLayers;
    }

    public ArrayList<MapLayer> getMapLayers() {
        return mapLayers;
    }

    public void setMapLayers(ArrayList<MapLayer> mapLayers) {
        this.mapLayers = mapLayers;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }
}

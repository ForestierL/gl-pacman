package engine.graphics;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class BackgroundDisplay extends Pane
{
    private int tileWidth, tileHeight;
    private ArrayList<GridLayer> gridLayers = new ArrayList<>();

    public BackgroundDisplay(int tileWidth, int tileHeight)
    {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public void add(GridLayer gridLayer)
    {
        gridLayer.resizeImageGrid(tileWidth, tileHeight);
        gridLayers.add(gridLayer);
    }

    public void addAll(ArrayList<GridLayer> gridLayers)
    {
        for(GridLayer gridLayer : gridLayers)
            add(gridLayer);
    }

    private void resizeAll()
    {
        for(GridLayer gridLayer : gridLayers)
            gridLayer.resizeImageGrid(tileWidth, tileHeight);
    }

    public ArrayList<GridLayer> getGridLayers()
    {
        return gridLayers;
    }

    public void setTileWidth(int tileWidth)
    {
        this.tileWidth = tileWidth;
        resizeAll();
    }

    public void setTileHeight(int tileHeight)
    {
        this.tileHeight = tileHeight;
        resizeAll();
    }
}

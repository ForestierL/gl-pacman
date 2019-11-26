package engine.graphics;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class BackgroundDisplay extends Pane
{
    private ArrayList<GridLayer> gridLayers = new ArrayList<>();

    public void add(GridLayer gridLayer)
    {
        gridLayers.add(gridLayer);
    }

    public void addAll(ArrayList<GridLayer> gridLayers)
    {
        this.gridLayers.addAll(gridLayers);
    }

    public ArrayList<GridLayer> getGridLayers() {
        return gridLayers;
    }
}

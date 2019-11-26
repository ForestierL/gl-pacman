package engine.graphics;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GridLayer extends GridPane
{

    public GridLayer()
    {
        super();
    }

    public void add(Sprite sprite, int i, int j)
    {
        ImageView imageView = sprite.getSpriteTexture();
        add(imageView, i, j);
    }

    public void resizeImageGrid(double newWidth, double newHeight)
    {
        for(Node child : getChildren())
        {
            ImageView imageView = (ImageView) child;
            imageView.setSmooth(false);
            imageView.setFitWidth(newWidth);
            imageView.setFitHeight(newHeight);
        }
    }

}

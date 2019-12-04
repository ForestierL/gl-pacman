package engine.graphics;

import javafx.scene.image.Image;

public class MovingSpriteTexture extends SpriteTexture{
    private int cpt;
    public MovingSpriteTexture(Image image){
        super(image);
    }


    public int[] getSubImageCoordinates(int subImageIdentifier)
    {   subImageIdentifier += cpt/12;
        int[] coordinates = new int[4];
        int srcX = (int) ((subImageIdentifier) % (image.getWidth() / cellWidth));
        int srcY = (int) ((subImageIdentifier) / (image.getWidth() / cellHeight));
        if(cpt == 47)
            cpt = 0;
        else
            this.cpt += 1;
        coordinates[0] = srcX  * cellWidth;
        coordinates[1] = srcY * cellHeight;
        coordinates[2] = cellWidth;
        coordinates[3] = cellHeight;

        return coordinates;
    }
}

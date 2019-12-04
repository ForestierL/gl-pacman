package engine.graphics;

import javafx.scene.image.Image;

public class ChangingMovingSpriteTexture extends MovingSpriteTexture {
    private int cpt;
    private Image image2;
    private Image image1;
    public ChangingMovingSpriteTexture(Image image1, Image image2){

        super(image1);
        this.image1 = image1;
        this.image2 = image2;
        cpt=0;}

        public int[] getSubImageCoordinates(int subImageIdentifier)
        {   subImageIdentifier += cpt/12;
            int[] coordinates = new int[4];
            int srcX = (int) ((subImageIdentifier) % (image.getWidth() / cellWidth));
            int srcY = (int) ((subImageIdentifier) / (image.getWidth() / cellHeight));
            if(cpt == 47){
                this.setImage(image2);
                cpt = 0;}
            else
                this.cpt += 1;
            coordinates[0] = srcX  * cellWidth;
            coordinates[1] = srcY * cellHeight;
            coordinates[2] = cellWidth;
            coordinates[3] = cellHeight;

            return coordinates;
        }
    }


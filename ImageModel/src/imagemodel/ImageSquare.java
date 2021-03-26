package imagemodel;
/** Represents calculations used to split images into squares*/
public class ImageSquare extends AbstractImageModel {

    /**
     * Sets fields with the required parameters.
     *
     * @param image the image operations will be applied on.
     */
    ImageSquare(int[][][] image) {
        super(image);
    }

//    protected int[] divideImage(int value) {
//        int[] squareInfo = new int[4];
//        float squareWidth = (float)image[0].length / value;
//        int numOfSuperPixelsRows = Math.round((float) image.length / squareWidth);
//        float squareHeight =(float)image.length / numOfSuperPixelsRows;
//        squareInfo[0] = value;
//        squareInfo[1] = numOfSuperPixelsRows;
//    }
}

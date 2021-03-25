package imagemodel;

import java.util.Random;

public class Mosaic extends AbstractImageModel implements Chunking {

    public Mosaic(int[][][] image) {
        super(image);
    }



    @Override
    public int[][][] apply(int value) {
        int[][] seedIndex = new int[0][];
        Random rand = new Random();
        int rowUpperbound = image.length;
        int colUpperbound = image[0].length;

        int randomRow = rand.nextInt(rowUpperbound);
        int randomCol = rand.nextInt(colUpperbound);
        seedIndex = generateSeed(value)
        return new int[0][][];
    }

    private double calculateDistanceBetweenPoints(
            double x1,
            double y1,
            double x2,
            double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}

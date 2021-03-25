package imagemodel;

import java.util.Random;

public class Mosaic implements Chunking {

    Mosaic



    @Override
    public int[][][] apply(int value) {
        int[][] seedIndex = new int[0][];
        Random rand = new Random();
        int rowUpperbound = ;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);
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

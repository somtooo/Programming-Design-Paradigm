package imagemodel;

import java.util.Random;

/**
 * Implements the Chunking interface and represents a class which applies the Mosaic operation
 * to an image.
 */
public class Mosaic extends AbstractImageModel implements Chunking {
  /**
   * Sets the fields with the required parameters.
   *
   * @param image the image to be pixelated.
   */
  public Mosaic(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] apply(int value) {
    int[][] seedIndex = new int[0][];
    seedIndex = generateSeed(value);

    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[0].length; col++) {
        int finalRow = 0;
        int finalCol = 0;
        double distance = Double.MAX_VALUE;
        for (int i = 0; i < value; i++) {
          int seedRowIndex = seedIndex[i][0];
          int seedColIndex = seedIndex[i][1];

          if (calculateDistanceBetweenPoints(row, col, seedRowIndex, seedColIndex) < distance) {
            distance = calculateDistanceBetweenPoints(row, col, seedRowIndex, seedColIndex);
            finalRow = seedRowIndex;
            finalCol = seedColIndex;
          }
        }
        image[row][col] = image[finalRow][finalCol];
      }
    }
    return image;
  }

  /**
   * Picks pixels at random and returns them in a 2d array.
   * @param value number of pixels in the array.
   * @return a 2d array containing the pixels.
   */
  private int[][] generateSeed(int value) {
    int[][] generatedSeeds = new int[value][2];
    Random rand = new Random();
    for (int i = 0; i < value; i++) {
      generatedSeeds[i][0] = rand.nextInt(image.length);
      generatedSeeds[i][1] = rand.nextInt(image[0].length);
    }
    return generatedSeeds;
  }

  /**
   * Calculates the distance between two points.
   * @param x1 the first point row value.
   * @param y1 the first point col value.
   * @param x2 the second point row value.
   * @param y2 the second point col value.
   * @return the distance as an int.
   */
  private double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
    return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
  }
}

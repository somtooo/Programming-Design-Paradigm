package imagemodel;

import static java.lang.Math.round;

/**
 * Implements the ImageModel interface and represents a class which applies the floyd dithering
 * algorithm to reduce the color in an image.
 */
public class FloydDithering extends AbstractReduceColorDensity {

  /**
   * Applies the floyd dithering algorithm to reduce the color in an image.
   *
   * @param image the image that the operation will be applied on.
   * @param intensity the intensity of the transformation, higher means a more color will be used
   *     and vice versa.
   * @throws IllegalArgumentException if intensity is negative or 0.
   */
  @Override
  public int[][][] apply(int[][][] image, int intensity) {
    checkIfZeroOrLess(intensity);
    return algorithm(image, intensity);
  }

  /**
   * Reduces the color in an image using the Floyd-Steinberg algorithm.
   *
   * @param image the image whose colors are to be reduced.
   * @param numberOfColors the number of colors used to represent the output image.
   * @return the image with its color reduced.
   */
  @Override
  int[][][] algorithm(int[][][] image, int numberOfColors) {
    for (int row = 0; row < (image.length - 1); row++) {
      for (int col = 1; col < image[0].length - 1; col++) {
        float red = image[row][col][0];
        float green = image[row][col][1];
        float blue = image[row][col][2];

        int newRed = round(numberOfColors * (red / 255)) * (255 / numberOfColors);
        int newGreen = round(numberOfColors * (green / 255)) * (255 / numberOfColors);
        int newBlue = round(numberOfColors * (blue / 255)) * (255 / numberOfColors);

        image[row][col][1] = newGreen;
        image[row][col][0] = newRed;
        image[row][col][2] = newBlue;

        float errRed = red - newRed;
        float errGreen = red - newGreen;
        float errBlue = red - newBlue;

        performErrorDiffusion(image, row, col, errRed, errGreen, errBlue);
      }
    }
    clamp(image);
    return image;
  }

  /**
   * Pushes the error calculated after getting new values for RGB to the surrounding pixels.
   *
   * @param image the image whose surrounding pixel values are used to add error.
   * @param row the row index of the image.
   * @param col the column index of the image.
   * @param errRed the error for the red color.
   * @param errGreen the error for the green color.
   * @param errBlue the error for the blue color.
   */
  private void performErrorDiffusion(
      int[][][] image, int row, int col, float errRed, float errGreen, float errBlue) {
    addError(image, row, col + 1, errRed, errGreen, errBlue, 7f / 16);
    addError(image, row + 1, col - 1, errRed, errGreen, errBlue, 3f / 16);
    addError(image, row + 1, col, errRed, errGreen, errBlue, 5f / 16);
    addError(image, row + 1, col + 1, errRed, errGreen, errBlue, 1f / 16);
  }

  /**
   * Adds the error and multiplier to the RGB values of a pixel.
   *
   * @param image the image whose pixel values are used to add error.
   * @param row the row index of the image to add the error to.
   * @param col the column index of the image to add the error to.
   * @param errRed the error to be added to the red color in the pixel.
   * @param errGreen the error to be added to the green color in the pixel.
   * @param errBlue the error to be added to the blue color in the pixel.
   */
  private void addError(
      int[][][] image,
      int row,
      int col,
      float errRed,
      float errGreen,
      float errBlue,
      float multiplier) {
    for (int color = 0; color < 3; color++) {

      if (color == 0) {
        float nRed = image[row][col][color] + (errRed * multiplier);
        image[row][col][color] = round(nRed);
      } else if (color == 1) {
        float nGreen = image[row][col][color] + (errGreen * multiplier);
        image[row][col][color] = round(nGreen);
      } else {
        float nBlue = image[row][col][color] + (errBlue * multiplier);
        image[row][col][color] = round(nBlue);
      }
    }
  }
}

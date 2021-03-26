package imagemodel;

/**
 * Implements the ImageModel interface and represents a class which applies the floyd dithering
 * algorithm to reduce the color in an image.
 */
public class FloydDithering extends AbstractReduceColorDensity implements Reduce {

  /**
   * Sets the fields with the required parameters.
   * @param image the image to be color reduced.
   */
  FloydDithering(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] reduce(int numOfColors) {
    checkIfZeroOrLess(numOfColors);
    return algorithm(image, numOfColors);
  }

  /**
   * Reduces the color in an image using the Floyd-Steinberg algorithm.
   *
   * @param image the image whose colors are to be reduced.
   * @param numberOfColors the number of colors used to represent the output image.
   * @return the image with its color reduced.
   */
  @Override
  protected int[][][] algorithm(int[][][] image, int numberOfColors) {
    checkIfNull(image);
    for (int row = 0; row < (image.length - 1); row++) {
      for (int col = 1; col < image[0].length - 1; col++) {
        int red = image[row][col][0];
        int green = image[row][col][1];
        int blue = image[row][col][2];
        int newRed = (numberOfColors * (red / 255) * (255 / numberOfColors));
        int newGreen = (numberOfColors * (green / 255) * (255 / numberOfColors));
        int newBlue = (numberOfColors * (blue / 255) * (255 / numberOfColors));
        image[row][col][0] = newRed;
        image[row][col][1] = newGreen;
        image[row][col][2] = newBlue;

        int errRed = red - newRed;
        int errGreen = green - newGreen;
        int errBlue = blue - newBlue;

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
      int[][][] image, int row, int col, int errRed, int errGreen, int errBlue) {
    addError(image, row, col + 1, errRed, errGreen, errBlue, 7);
    addError(image, row + 1, col - 1, errRed, errGreen, errBlue, 3);
    addError(image, row + 1, col, errRed, errGreen, errBlue, 5);
    addError(image, row + 1, col + 1, errRed, errGreen, errBlue, 1);
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
      int[][][] image, int row, int col, int errRed, int errGreen, int errBlue, int multiplier) {
    for (int color = 0; color < 3; color++) {

      if (color == 0) {
        int nRed = image[row][col][color] + errRed * multiplier / 16;
        image[row][col][color] = nRed;
      } else if (color == 1) {
        int nGreen = image[row][col][color] + errGreen * multiplier / 16;
        image[row][col][color] = nGreen;
      } else {
        int nBlue = image[row][col][color] + errBlue * multiplier / 16;
        image[row][col][color] = nBlue;
      }
    }
  }



}

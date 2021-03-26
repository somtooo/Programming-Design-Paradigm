package imagemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Chunking interface and represents a class which applies the Pixelation operation
 * to an image.
 */
public class Pixelation extends AbstractImageModel implements Chunking {

  /**
   * Sets the fields with the required parameters.
   *
   * @param image the image to be pixelated.
   */
  public Pixelation(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] apply(int value) {
    float squareWidth = (float)image[0].length / value;
    int numOfSuperPixelsRows = Math.round((float) image.length / squareWidth);
    float squareHeight =(float)image.length / numOfSuperPixelsRows;

    for (int superRow = 0; superRow < numOfSuperPixelsRows; superRow++) {
      for (int superCol = 0; superCol < value; superCol++) {
        pixelate(image, superRow, superCol, squareHeight, squareWidth);
      }
    }
    return image;
  }


  /**
   * Replaces the all pixels with colors of their middle super pixel value.
   * @param image the image to work on.
   * @param superRow the row of the super pixel.
   * @param superCol the col of the super pixel.
   * @param squareHeight the height of the super pixel.
   * @param squareWidth the width of the super pixel.
   */
  private void pixelate(
      int[][][] image, int superRow, int superCol, float squareHeight, float squareWidth) {
    int pixelIndex;
    List<int[]> pixels = new ArrayList<int[]>();
    getAllPixels(image, superRow, superCol, squareHeight, squareWidth, pixels);
    int[] channels = new int[3];
    findChannelOfMiddlePixel(pixels, channels);

    replacePixelValue(pixels, channels);
  }

  /**
   * Gets all the pixels in a super pixel.
   * @param image the image to work on.
   * @param superRow the row of the super pixel.
   * @param superCol the col of the super pixel.
   * @param squareHeight the height of the super pixel.
   * @param squareWidth the width of the super pixel.
   * @param pixels the list to store the pixels in.
   */
  private void getAllPixels(int[][][] image, int superRow, int superCol, float squareHeight, float squareWidth, List<int[]> pixels) {
    for (int pixelRow = Math.round(superRow * squareHeight);
         pixelRow < (superRow + 1) * squareHeight;
         pixelRow++) {
      for (int pixelCol = Math.round(superCol * squareWidth);
           pixelCol < (superCol + 1) * squareWidth;
           pixelCol++) {
        pixels.add(image[pixelRow][pixelCol]);
      }
    }
  }

  /**
   * Gets the channel values of the middle pixel in a super pixel.
   * @param pixels the pixels in a super pixel.
   * @param channels the array to store the channel
   */
  private void findChannelOfMiddlePixel(List<int[]> pixels, int[] channels) {
    int pixelIndex;
    if (pixels.size() % 2 == 1) {
      pixelIndex = (pixels.size() / 2 + pixels.size() % 2) - 1;
      channels[0] = pixels.get(pixelIndex)[0];
      channels[1] = pixels.get(pixelIndex)[1];
      channels[2] = pixels.get(pixelIndex)[2];

    } else {
      pixelIndex = pixels.size() / 2;
      channels[0] = (pixels.get(pixelIndex - 1)[0] + pixels.get(pixelIndex)[0]) / 2;
      channels[1] = (pixels.get(pixelIndex - 1)[1] + pixels.get(pixelIndex)[1]) / 2;
      channels[2] = (pixels.get(pixelIndex - 1)[2] + pixels.get(pixelIndex)[2]) / 2;
    }
  }

  /**
   * Replaces the all pixels with colors of their middle super pixel value.
   * @param pixels pixel whose color is to be replaced.
   * @param channels the middle pixels color.
   */
  private void replacePixelValue(List<int[]> pixels, int[] channels) {
    for (int[] pixel : pixels) {
      pixel[0] = channels[0];
      pixel[1] = channels[1];
      pixel[2] = channels[2];
    }
  }




}

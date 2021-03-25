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
    float squareWidth = (float) image[0].length / value;
    int numOfSuperPixelsRows = Math.round((float) image.length / squareWidth);
    float squareHeight = (float) image.length / numOfSuperPixelsRows;

    for (int superRow = 0; superRow < numOfSuperPixelsRows; superRow++) {
      for (int superCol = 0; superCol < value; superCol++) {
        createSquare(image, superRow, superCol, squareHeight, squareWidth);
      }
    }
    return image;
  }

  private void createSquare(
      int[][][] image, int superRow, int superCol, float squareHeight, float squareWidth) {
    int pixelIndex;
    int red;
    int green;
    int blue;
    List<int[]> pixels = new ArrayList<int[]>();
    for (int pixelRow = Math.round(superRow * squareHeight);
        pixelRow < (superRow + 1) * squareHeight;
        pixelRow++) {
      for (int pixelCol = Math.round(superCol * squareWidth);
          pixelCol < (superCol + 1) * squareWidth;
          pixelCol++) {
        pixels.add(image[pixelRow][pixelCol]);
      }
    }

    if (pixels.size() % 2 == 1) {
      pixelIndex = (pixels.size() / 2 + pixels.size() % 2) - 1;
      red = pixels.get(pixelIndex)[0];
      green = pixels.get(pixelIndex)[1];
      blue = pixels.get(pixelIndex)[2];

    } else {
      pixelIndex = pixels.size() / 2;
      red = (pixels.get(pixelIndex - 1)[0] + pixels.get(pixelIndex)[0]) / 2;
      green = (pixels.get(pixelIndex - 1)[1] + pixels.get(pixelIndex)[1]) / 2;
      blue = (pixels.get(pixelIndex - 1)[2] + pixels.get(pixelIndex)[2]) / 2;
    }

    for (int[] pixel : pixels) {
      pixel[0] = red;
      pixel[1] = green;
      pixel[2] = blue;
    }
  }


}

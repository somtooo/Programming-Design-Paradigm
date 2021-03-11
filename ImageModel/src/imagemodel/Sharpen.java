package imagemodel;

/**
 * Implements the ImageModel interface and represents a class which applies the sharpen filtering
 * operation to an image.
 */
public class Sharpen extends AbstractFilter {

  /**
   * Applies the sharpen filtering operation on an image.
   *
   * @param image the image that the operation will be applied on.
   * @param intensity the intensity of the sharpening, higher means a more sharpened image.
   * @throws IllegalArgumentException if intensity is negative or 0.
   */
  @Override
  public int[][][] apply(int[][][] image, int intensity) {
    checkIfNull(image);
    checkIfZeroOrLess(intensity);

    float[][] sharpen =
        new float[][] {
          {-1f / 8, -1f / 8, -1f / 8, -1f / 8, -1f / 8},
          {-1f / 8, 1f / 4, 1f / 4, 1f / 4, -1f / 8},
          {-1f / 8, 1f / 4, 1f, 1f / 4, -1f / 8},
          {-1f / 8, 1f / 4, 1f / 4, 1f / 4, -1f / 8},
          {-1f / 8, -1f / 8, -1f / 8, -1f / 8, -1f / 8}
        };

    for (int num = 0; num < intensity; num++) {
      convolve(image, sharpen);
    }

    return image;
  }
}

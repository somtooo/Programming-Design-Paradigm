package imagemodel;

/**
 * Implements the ImageModel interface and represents a class which applies the blur filtering
 * operation to an image.
 */
public class Blur extends AbstractFilter {

  /**
   * Applies the blur filtering operation on an image.
   *
   * @param image the image that the operation will be applied on.
   * @param intensity the intensity of the blur, higher means a more blurred image.
   * @throws IllegalArgumentException if intensity is negative or 0.
   */
  @Override
  public int[][][] apply(int[][][] image, int intensity) {
    checkIfNull(image);
    checkIfZeroOrLess(intensity);
    float[][] blur =
        new float[][] {
          {1f / 16, 1f / 8, 1f / 16}, {1f / 16, 1f / 8, 1f / 16}, {1f / 16, 1f / 8, 1f / 16}
        };

    for (int num = 0; num < intensity; num++) {
      convolve(image, blur);
    }

    return image;
  }
}

package imagemodel;

/**
 * Implements the ImageModel interface and represents a class which applies the sharpen filtering
 * operation to an image.
 */
public class Sharpen extends AbstractFilter implements Filter {

  /**
   * Sets the field with the required parameters.
   *
   * @param image the image to be sharpened.
   */
  Sharpen(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] applyFilter(int intensity) {
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

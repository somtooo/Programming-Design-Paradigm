package imagemodel;

/**
 * Implements the ImageModel interface and represents a class which applies the blur filtering
 * operation to an image.
 */
public class Blur extends AbstractFilter implements Filter {

  /**
   * Sets the field with the required parameters.
   * @param image the image to blur.
   */
  Blur(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] applyFilter(int intensity) {
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

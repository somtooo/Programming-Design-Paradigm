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
  public Blur(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] applyFilter(int intensity) {
    checkIfZeroOrLess(intensity);
    int[][][] copyImage = deepCopy(image);
    float[][] blur =
            new float[][] {
              {1f / 16, 1f / 8, 1f / 16}, {1f / 8, 1f / 4, 1f / 8}, {1f / 16, 1f / 8, 1f / 16}
            };

    for (int num = 0; num < intensity; num++) {
      convolve(copyImage, blur);
    }


    return copyImage;
  }
}

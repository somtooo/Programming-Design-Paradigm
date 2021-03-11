package imagemodel;

/**
 * Implements the ImageModel interface and represents a class which applies the Sepia color
 * transform operation to an image.
 */
public class Sepia extends AbstractColorTransformation {

  /**
   * Applies the Sepia color transform operation on an image.
   *
   * @param image the image that the operation will be applied on.
   * @param intensity the intensity of the transformation, higher means a more brighter sepia image.
   * @throws IllegalArgumentException if intensity is negative or 0.
   */
  @Override
  public int[][][] apply(int[][][] image, int intensity) {
    checkIfNull(image);
    checkIfZeroOrLess(intensity);

    float[][] sepia =
        new float[][] {
          {0.393f, 0.769f, 0.189f}, {0.349f, 0.686f, 0.168f}, {0.272f, 0.534f, 0.131f}
        };

    for (int num = 0; num < intensity; num++) {
      colorTransformation(image, sepia);
    }

    return image;
  }
}

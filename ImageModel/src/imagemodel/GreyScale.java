package imagemodel;

/**
 * Implements the ImageModel interface and represents a class which applies the Greyscale color
 * transform operation to an image.
 */
public class GreyScale extends AbstractColorTransformation {

  /**
   * Applies the Greyscale color transform operation on an image.
   *
   * @param image the image that the operation will be applied on.
   * @param intensity the intensity of the transformation, higher means a more darker greyscale
   *     image.
   * @throws IllegalArgumentException if intensity is negative or 0.
   */
  @Override
  public int[][][] apply(int[][][] image, int intensity) {
    checkIfZeroOrLess(intensity);
    float[][] greyScale =
        new float[][] {
            {0.2126f, 0.7152f, 0.0722f}, {0.2126f, 0.7152f, 0.0722f}, {0.2126f, 0.7152f, 0.0722f}
          };

    for (int num = 0; num < intensity; num++) {
      colorTransformation(image, greyScale);
    }
    return image;
  }
}

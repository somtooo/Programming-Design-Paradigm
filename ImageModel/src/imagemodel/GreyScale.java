package imagemodel;

/**
 * Implements the ImageModel interface and represents a class which applies the Greyscale color
 * transform operation to an image.
 */
public class GreyScale extends AbstractColorTransformation implements Transform {


  /**
   * Sets the fields with the required parameters.
   *
   * @param image the image to be transformed to greyscale.
   */
  public GreyScale(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] applyTransform() {
    int[][][] copyImage = deepCopy(image);
    float[][] greyScale =
            new float[][] {
              {0.2126f, 0.7152f, 0.0722f}, {0.2126f, 0.7152f, 0.0722f}, {0.2126f, 0.7152f, 0.0722f}
            };
    colorTransformation(copyImage, greyScale);
    return copyImage;
  }
}

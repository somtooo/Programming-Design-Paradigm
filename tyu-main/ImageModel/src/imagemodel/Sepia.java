package imagemodel;

/**
 * Implements the ImageModel interface and represents a class which applies the Sepia color
 * transform operation to an image.
 */
public class Sepia extends AbstractColorTransformation implements Transform {

  /**
   * Sets the fields with the required parameters.
   * @param image the image to be transformed to sepia.
   */
  Sepia(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] applyTransform(int[][][] image) {
    checkIfNull(image);
    float[][] sepia =
            new float[][] {
                    {0.393f, 0.769f, 0.189f}, {0.349f, 0.686f, 0.168f}, {0.272f, 0.534f, 0.131f}
            };
    colorTransformation(image, sepia);
    return image;
  }
}

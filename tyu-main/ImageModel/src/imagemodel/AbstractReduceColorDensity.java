package imagemodel;

/** Represents calculations used to reduce colors in an image. */
public abstract class AbstractReduceColorDensity extends AbstractImageModel {

  /**
   * Sets the field with the required parameters.
   * @param image the image a color reduction will be applied on.
   */
  public AbstractReduceColorDensity(int[][][] image) {
    super(image);
  }

  /**
   * The algorithmic calculation used for the color reduction.
   *
   * @param image the image whose colors are to be reduced.
   * @param numberOfColors the number of colors used to represent the output image.
   * @return the image with the colors reduced.
   */
  abstract  int[][][] algorithm(int[][][] image, int numberOfColors);
}

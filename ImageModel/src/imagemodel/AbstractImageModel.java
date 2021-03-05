package imagemodel;

/** Represents calculations used to make sure images are saved correctly. */
public abstract class AbstractImageModel implements ImageModel {

  /**
   * Goes through the image to make sure the RGB values are between 0 and 255.
   *
   * @param image the image to check through
   */
  protected void clamp(int[][][] image) {
    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[row].length; col++) {

        for (int i = 0; i < 3; i++) {
          if (image[row][col][i] < 0) {
            image[row][col][i] = 0;
          } else if (image[row][col][i] > 255) {
            image[row][col][i] = 255;
          }
        }
      }
    }
  }

  /**
   * Checks if the intensity values if equal to or less than zero.
   *
   * @param intensity the value to be checked.
   * @throws IllegalArgumentException if the check is true.
   */
  protected void checkIfZeroOrLess(int intensity) {
    if (intensity <= 0) {
      throw new IllegalArgumentException("Intensity cant be zero");
    }
  }
}

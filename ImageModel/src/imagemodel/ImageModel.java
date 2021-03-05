package imagemodel;

/** Represents filtering and transformation operations that can be performed on an image. */
public interface ImageModel {
  /**
   * Applies filtering or transformation operation on an image based on which class instantiates it.
   *
   * @param image the image that the operation will be applied on.
   * @param intensity the intensity of the image application which depends on the operation for
   *     example a high intensity sepia will produce a lighter image while a high intensity color
   *     reduction will use more colors and vice versa.
   * @throws IllegalArgumentException if intensity is negative or 0.
   */
  int[][][] apply(int[][][] image, int intensity);
}

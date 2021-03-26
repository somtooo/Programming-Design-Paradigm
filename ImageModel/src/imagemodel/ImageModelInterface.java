package imagemodel;

import java.io.IOException;

/** Represents filtering and transformation operations that can be performed on an image. */
public interface ImageModelInterface {
  /**
   * Applies filtering or transformation operation on an image based on which class instantiates it.
   *
   * @param intensity the intensity of the image application which depends on the operation for
   *     example a high intensity sepia will produce a lighter image while a high intensity color
   *     reduction will use more colors and vice versa.
   * @throws IllegalArgumentException if intensity is negative or 0.
   * @throws IllegalArgumentException if image is null.
   */
  int[][][] blur(int intensity) throws IllegalStateException;

  int[][][] sharpen(int intensity) throws IllegalStateException;

  int[][][] greyScale() throws IllegalStateException;

  int[][][] sepia() throws IllegalStateException;

  int[][][] reduceColor(int numberOfColors) throws IllegalStateException;

  int[][][] toMosaic(int seeds) throws IllegalStateException;

  int[][][] pixelate(int squares) throws IllegalStateException;

  String crossStitch() throws IllegalStateException;

  int[][][] loadImage(String filename) throws IOException;

  void saveImage(String filename) throws IllegalStateException,IOException;

  void saveTextToFile(String filename) throws IOException;





}

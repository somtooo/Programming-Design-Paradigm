package imagemodel;

import java.io.IOException;

/** Represents filtering and transformation operations that can be performed on an image. */
public interface ImageModelInterface {
  /**
   * Applies the blur operation on an image.
   * @param intensity the intensity of the blur e.g higher means more blurred.
   * @throws IllegalStateException if the image is empty.
   */
  void blur(int intensity) throws IllegalStateException;

  /**
   * Applies the sharpen operation on an image.
   * @param intensity the intensity of the sharpening e.g higher means more sharpened.
   * @throws IllegalStateException if the image is empty.
   */
  void sharpen(int intensity) throws IllegalStateException;

  /**
   * Applies the greyScale operation on an image.
   * @throws IllegalStateException if the image is empty.
   */
  void greyScale() throws IllegalStateException;

  /**
   * Applies the sepia operation on an image.
   * @throws IllegalStateException if the image is empty.
   */
  void sepia() throws IllegalStateException;


  /**
   * Applies the reduce color operation on an image.
   * @param numberOfColors the number of colors in the output image.
   * @throws IllegalStateException if the image is empty.
   */
  void reduceColor(int numberOfColors) throws IllegalStateException;

  /**
   * Applies the mosaic operation on an image.
   * @param seeds the number of random seeds to be used.
   * @throws IllegalStateException if the image is empty.
   */
  void toMosaic(int seeds) throws IllegalStateException;

  /**
   * Applies the pixelate operation on an image.
   * @param squares the number of squares across the width to be used.
   * @throws IllegalStateException if the image is empty.
   */
  void pixelate(int squares) throws IllegalStateException;

  /**
   * Applies the crossStitch operation on an image.
   * @throws IllegalStateException if the image is empty.
   */
  void crossStitch() throws IllegalStateException;

  /**
   * Loads an image and returns a 2d array representing the image.
   * @param filename the name of the file.
   * @return 2d array that represents the image.
   * @throws IOException if something goes wrong with finding the file.
   */
  int[][][] loadImage(String filename) throws IOException;

  /**
   * Saves an image to a file.
   * @param filename the filename of the saved image.
   * @throws IllegalStateException if the image is empty.
   * @throws IOException if something goes wrong with saving.
   */
  void saveImage(String filename) throws IllegalStateException,IOException;

  /**
   * Writes string values to a text file.
   * @param filename the filename of the file.
   * @throws IOException if something goes wrong with writing to file.
   */
  void saveTextToFile(String filename) throws IOException;





}

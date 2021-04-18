package imagemodel;

import controller.TotalFeatures;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/** Represents filtering and transformation operations that can be performed on an image. */
public interface ImageModelInterface {
  /**
   * Registers Student grade observer/listener to receive updates on changes.
   */
  void attach(TotalFeatures observer);

  /**
   * Removes Student grade observer/listener to stop receiving updates on
   * changes.
   */
  void detach(TotalFeatures observer);

  /**
   * Adds a new record to Student Grade.
   */
  void notifyOfImageChange();

  /**
   * Applies the blur operation on an image.
   *
   * @param intensity the intensity of the blur e.g higher means more blurred.
   * @throws IllegalStateException if the image is empty.
   * @return the blurred image;
   */
  int[][][] blur(int intensity) throws IllegalStateException;

  /**
   * Applies the sharpen operation on an image.
   *
   * @param intensity the intensity of the sharpening e.g higher means more sharpened.
   * @throws IllegalStateException if the image is empty.
   * @return the sharpened image.
   */
  int[][][] sharpen(int intensity) throws IllegalStateException;

  /**
   * Applies the greyScale operation on an image.
   *
   * @throws IllegalStateException if the image is empty.
   * @return the greyscaled image.
   */
  int[][][] greyScale() throws IllegalStateException;

  /**
   * Applies the sepia operation on an image.
   *
   * @throws IllegalStateException if the image is empty.
   * @return the sepia image.
   */
  int[][][] sepia() throws IllegalStateException;

  /**
   * Applies the reduce color operation on an image.
   *
   * @param numberOfColors the number of colors in the output image.
   * @throws IllegalStateException if the image is empty.
   * @return the reduced color image.
   */
  int[][][] reduceColor(int numberOfColors) throws IllegalStateException;

  /**
   * Applies the mosaic operation on an image.
   *
   * @param seeds the number of random seeds to be used.
   * @throws IllegalStateException if the image is empty.
   * @return the mosaic image.
   */
  int[][][] toMosaic(int seeds) throws IllegalStateException;

  /**
   * Applies the pixelate operation on an image.
   *
   * @param squares the number of squares across the width to be used.
   * @throws IllegalStateException if the image is empty.
   * @return the pixelated image.
   */
  int[][][] pixelate(int squares) throws IllegalStateException;

  /**
   * Applies the crossStitch operation on an image.
   *
   * @throws IllegalStateException if the image is empty.
   */
  void crossStitch() throws IllegalStateException;

  /**
   * Loads an image and returns a 2d array representing the image.
   *
   * @param filename the name of the file.
   * @throws IOException if something goes wrong with finding the file.
   */
  void loadImage(String filename) throws IOException;

  /**
   * Saves an image to a file.
   *
   * @param filename the filename of the saved image.
   * @throws IllegalStateException if the image is empty.
   * @throws IOException if something goes wrong with saving.
   */
  void saveImage(String filename) throws IllegalStateException, IOException;

  /**
   * Writes string values to a text file.
   *
   * @param filename the filename of the file.
   * @throws IOException if something goes wrong with writing to file.
   */
  void savePattern(String filename) throws IOException;



    /**
   * Gets a buffered image from an array image
   * @return the buffered image.
   */
  BufferedImage getBufferedImage();

  /**
   * Gets the dmc color value code names.
   * @return the dmc values.
   */
    String[] getDmcValues();

  /**
   * Gets the corresponding rgb for a dmc value.
   * @param selectedValue the dmc code to use and check.
   * @return the corresponding rgb.
   */
  int[] getDmcRgb(String selectedValue);

  /**
   * Updates the color in the specified index with a new one.
   * @param color the new color to be used.
   * @param x the x index of the color.
   * @param y the y index of the color.
   */
  void updateColorInImage(String color, int x, int y);


  /**
   * Gets the colors used to generate a pattern.
   * @return a list containing the used colors.
   */
  List<String> getDmcColorsUsed();

  void setDmc(List<String> colorToUse);

  String getLegendIcon(String dmcValue);

  String getPattern();

  void removeColorFromImage(int yCoordinate, int xCoordinate);
}

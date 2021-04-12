package imagemodel;

import imagemodel.utilities.FileUtilities;
import imagemodel.utilities.ImageUtilities;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Implements the Image Model Interface and represents image processing operations that can be done
 * on an image.
 */
public class ImageModel implements ImageModelInterface {
  private String pattern;

  /** Default Constructor. */
  public ImageModel() {
    this.pattern = "";
  }

  @Override
  public void blur(int intensity, int[][][] image) throws IllegalStateException {
    checkState(image);
    Filter blur = new Blur(image);
    blur.applyFilter(intensity);
  }

  /**
   * Checks if an image array is empty.
   *
   * @param image the image to check.
   * @throws IllegalStateException if the array is empty.
   * @throws IllegalArgumentException if the image is null.
   */
  private void checkState(int[][][] image) throws IllegalStateException, IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
  }

  @Override
  public void sharpen(int intensity, int[][][] image) throws IllegalStateException {
    checkState(image);
    Filter sharpen = new Sharpen(Objects.requireNonNull(image));
    sharpen.applyFilter(intensity);
  }

  @Override
  public void greyScale(int[][][] image) throws IllegalStateException {
    checkState(image);
    Transform greyScale = new GreyScale(Objects.requireNonNull(image));
    greyScale.applyTransform();
  }

  @Override
  public void sepia(int[][][] image) throws IllegalStateException {
    checkState(image);
    Transform sepia = new Sepia(Objects.requireNonNull(image));
    sepia.applyTransform();
  }

  @Override
  public void reduceColor(int numberOfColors, int[][][] image) throws IllegalStateException {
    checkState(image);
    Reduce colorReduce = new FloydDithering(Objects.requireNonNull(image));
    colorReduce.reduce(numberOfColors);
  }

  @Override
  public void toMosaic(int seeds, int[][][] image) throws IllegalStateException {
    checkState(image);
    Chunking mosaic = new Mosaic(Objects.requireNonNull(image));
    mosaic.apply(seeds);
  }

  @Override
  public void pixelate(int squares, int[][][] image) throws IllegalStateException {
    checkState(image);
    Chunking pixelate = new Pixelation(Objects.requireNonNull(image));
    pixelate.apply(squares);
  }

  @Override
  public void crossStitch(int[][][] image) throws IllegalStateException {
    checkState(image);
    Pattern crossStitch = new CrossStitch(Objects.requireNonNull(image));
    this.pattern = crossStitch.generate();
  }

  @Override
  public int[][][] loadImage(String filename) throws IOException {
    Objects.requireNonNull(filename);
    File file = new File("");
    String path = file.getAbsolutePath() + "\\" + filename;
    return ImageUtilities.readImage(path);
  }

  @Override
  public void saveImage(String filename, int[][][] image)
      throws IllegalStateException, IOException {
    checkState(image);
    Objects.requireNonNull(filename);
    ImageUtilities.writeImage(
        image, Objects.requireNonNull(image)[0].length, image.length, filename);
  }

  @Override
  public void savePattern(String filename) throws IOException {
    Objects.requireNonNull(filename);
    FileUtilities.writeToFile(filename, pattern);
  }
}

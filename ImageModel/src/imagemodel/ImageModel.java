package imagemodel;

import imagemodel.utilities.FileUtilities;
import imagemodel.utilities.ImageUtilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Implements the Image Model Interface and represents image processing operations that can be done
 * on an image.
 */
public class ImageModel extends AbstractImageModel implements ImageModelInterface  {
  private String pattern;
  private Filter blur;
  private Filter sharpen;
  private Transform greyscale;
  private Transform sepia;
  private Reduce colorReduce;
  private Chunking mosaic;
  private Chunking pixelate;
  private Pattern crossStitch;
  private int[][][] currentModifiedImage;

  /** Default Constructor. */
  public ImageModel() {
    super(new int[0][][]);
    this.pattern = "";
    currentModifiedImage = new int[0][][];
    blur = new Blur(new int[0][][]);
    sharpen = new Sharpen(new int[0][][]);
    greyscale = new GreyScale(new int[0][][]);
    sepia = new Sepia(new int[0][][]);
    colorReduce = new FloydDithering(new int[0][][]);
    mosaic = new Mosaic(new int[0][][]);
    pixelate = new Pixelation(new int[0][][]);
    crossStitch = new CrossStitch(new int[0][][]);
  }

  @Override
  public int[][][] blur(int intensity) throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = blur.applyFilter(intensity);
    return deepCopy(currentModifiedImage);
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
  public int[][][] sharpen(int intensity) throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = sharpen.applyFilter(intensity);
    return deepCopy(currentModifiedImage);
  }

  @Override
  public int[][][] greyScale() throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = greyscale.applyTransform();
    return deepCopy(currentModifiedImage);
  }

  @Override
  public int[][][] sepia() throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = sepia.applyTransform();
    return deepCopy(currentModifiedImage);
  }

  @Override
  public int[][][] reduceColor(int numberOfColors) throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = colorReduce.reduce(numberOfColors);
    return deepCopy(currentModifiedImage);
  }

  @Override
  public int[][][] toMosaic(int seeds) throws IllegalStateException {
    System.out.println(seeds);
    checkState(currentModifiedImage);
    currentModifiedImage = mosaic.apply(seeds);
    return deepCopy(currentModifiedImage);
  }

  @Override
  public int[][][] pixelate(int squares) throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = pixelate.apply(squares);
    return deepCopy(currentModifiedImage);
  }

  @Override
  public void crossStitch() throws IllegalStateException {
    checkState(currentModifiedImage);
    this.pattern = crossStitch.generate();
  }

  @Override
  public void loadImage(String filename) throws IOException {
    Objects.requireNonNull(filename);
//    File file = new File("");
//    String path = file.getAbsolutePath() + "\\" + filename;
    currentModifiedImage =  ImageUtilities.readImage(filename);
    int[][][] loadImage = ImageUtilities.readImage(filename);
    blur = new Blur(loadImage);
    sharpen = new Sharpen(loadImage);
    greyscale = new GreyScale(loadImage);
    sepia = new Sepia(loadImage);
    colorReduce = new FloydDithering(loadImage);
    pixelate = new Pixelation(loadImage);
    mosaic = new Mosaic(loadImage);
    crossStitch = new CrossStitch(loadImage);
  }

  @Override
  public void saveImage(String filename)
      throws IllegalStateException, IOException {
    checkState(currentModifiedImage);
    Objects.requireNonNull(filename);
    ImageUtilities.writeImage(
        currentModifiedImage, currentModifiedImage[0].length, currentModifiedImage.length, filename);
  }

  @Override
  public void savePattern(String filename) throws IOException {
    Objects.requireNonNull(filename);
    FileUtilities.writeToFile(filename, pattern);
  }

  @Override
  public BufferedImage getBufferedImage() {
    return ImageUtilities.getBufferedImage(currentModifiedImage,currentModifiedImage[0].length,currentModifiedImage.length);
  }


}

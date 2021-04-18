package imagemodel;

import controller.TotalFeatures;
import imagemodel.utilities.FileUtilities;
import imagemodel.utilities.ImageUtilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Implements the Image Model Interface and represents image processing operations that can be done
 * on an image.
 */
public class ImageModel extends AbstractImageModel implements ImageModelInterface  {
  private String pattern;
  private String patternWithoutLegend;
  private Filter blur;
  private Filter sharpen;
  private Transform greyscale;
  private Transform sepia;
  private Reduce colorReduce;
  private Chunking mosaic;
  private Chunking pixelate;
  private Pattern crossStitch;
  private int[][][] currentModifiedImage;
  private List<String> dmcColorsUsed;
  private final Dmc dmc;
  private final List<TotalFeatures> observers;

  /** Default Constructor. */
  public ImageModel() {
    super(new int[0][][]);
    patternWithoutLegend = "";
    dmcColorsUsed = new ArrayList<>();
    currentModifiedImage = new int[0][][];
    blur = new Blur(new int[0][][]);
    sharpen = new Sharpen(new int[0][][]);
    greyscale = new GreyScale(new int[0][][]);
    pattern = "";
    observers = new ArrayList<>();
    sepia = new Sepia(new int[0][][]);
    colorReduce = new FloydDithering(new int[0][][]);
    mosaic = new Mosaic(new int[0][][]);
    pixelate = new Pixelation(new int[0][][]);
    crossStitch = new CrossStitch(new int[0][][]);
    dmc = new Dmc();
  }



  @Override
  public void attach(TotalFeatures observer) {
    observers.add(observer);
  }


  @Override
  public void detach(TotalFeatures observer) {
    observers.remove(observer);
  }


  @Override
  public void notifyOfImageChange() {
    for (TotalFeatures observer : observers) {
      observer.update();
    }
  }

  @Override
  public int[][][] blur(int intensity) throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = blur.applyFilter(intensity);
    notifyOfImageChange();
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
    notifyOfImageChange();
    return deepCopy(currentModifiedImage);
  }

  @Override
  public int[][][] greyScale() throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = greyscale.applyTransform();
    notifyOfImageChange();
    return deepCopy(currentModifiedImage);
  }

  @Override
  public int[][][] sepia() throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = sepia.applyTransform();
    notifyOfImageChange();
    return deepCopy(currentModifiedImage);
  }

  @Override
  public int[][][] reduceColor(int numberOfColors) throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = colorReduce.reduce(numberOfColors);
    notifyOfImageChange();
    return deepCopy(currentModifiedImage);
  }

  @Override
  public int[][][] toMosaic(int seeds) throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = mosaic.apply(seeds);
    notifyOfImageChange();
    return deepCopy(currentModifiedImage);
  }

  @Override
  public int[][][] pixelate(int squares) throws IllegalStateException {
    checkState(currentModifiedImage);
    currentModifiedImage = pixelate.apply(squares);
    notifyOfImageChange();
    return deepCopy(currentModifiedImage);
  }

  @Override
  public void crossStitch() throws IllegalStateException {
    checkState(currentModifiedImage);
    this.pattern = crossStitch.generate();
    currentModifiedImage = crossStitch.getImagePattern();
    dmcColorsUsed = new ArrayList<>(crossStitch.getUsedColors());
    patternWithoutLegend = crossStitch.getStringIcon();
    notifyOfImageChange();

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

    notifyOfImageChange();

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

  @Override
  public String[] getDmcValues() {
    return dmc.getDmcFlossValues();
  }

  @Override
  public int[] getDmcRgb(String selectedValue) {
    return dmc.getRgb(selectedValue);
  }

  @Override
  public void updateColorInImage(String color, int x, int y) {
    int[] newColor = dmc.getRgb(color);
    int[] removeColor = currentModifiedImage[y][x];
    for (int row = 0; row < currentModifiedImage.length ; row++) {
      for (int col = 0; col < currentModifiedImage[row].length; col++) {
        if (Arrays.equals(removeColor, currentModifiedImage[row][col])) {
          currentModifiedImage[row][col] = newColor;
        }
      }
    }
    dmcColorsUsed.remove(dmc.getDmcCodeFromRgb(removeColor));
    dmcColorsUsed.add(color);
    notifyOfImageChange();
  }

  @Override
  public List<String> getDmcColorsUsed() {
    System.out.println(dmcColorsUsed);
    return new ArrayList<>(dmcColorsUsed);
  }

  @Override
  public void setDmc(List<String> colorToUse) {
    dmc.setDmcColors(colorToUse);
    crossStitch.setDmc(dmc);
  }

  @Override
  public String getLegendIcon(String dmcValue) {
    return dmc.getIcon(dmcValue);
  }

  @Override
  public String getPattern() {
    return patternWithoutLegend;
  }

  @Override
  public void removeColorFromImage(int yCoordinate, int xCoordinate) {
    int[] removeColor = currentModifiedImage[yCoordinate][xCoordinate];
    String dmcColorName = dmc.getDmcCodeFromRgb(removeColor);
    dmcColorsUsed.remove(dmcColorName);
    patternWithoutLegend = patternWithoutLegend.replace(getLegendIcon(dmcColorName),".");
    notifyOfImageChange();

  }




}

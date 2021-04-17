package imagemodel;

import imagemodel.utilities.FileUtilities;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements the Chunking interface and represents a class which applies the Pixelation operation
 * to an image.
 */
public class CrossStitch extends AbstractImageModel implements Pattern {

  private int[][][] crossedImage;

  /**
   * Default Constructor.
   *
   * @param image image to be generated into a cross stitch pattern.
   */
  public CrossStitch(int[][][] image) {
    super(image);

  }

  @Override
  public String generate() {

    File csv = new File("");
    String path = csv.getAbsolutePath() + "\\DMC Cotton Floss converted to RGB Values.csv";
    String[][] dmc = new String[0][];
    try {
      dmc = FileUtilities.loadCsvFile(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    List<String> legend = getLegend();
    replaceColumn(dmc, 1, legend);
    Chunking pixelate = new Pixelation(image);
    int squares = 100;
    float squareWidth = (float) image[0].length / squares;
    int numOfSuperPixelsRows = Math.round((float) image.length / squareWidth);
    float squareHeight = (float) image.length / numOfSuperPixelsRows;

//    this.image = pixelate.apply(squares);
    Map<String, String> legendMap = new HashMap<>();
    StringBuilder builder = new StringBuilder();
    builder.append(numOfSuperPixelsRows).append(" x ").append(squares).append("\n");
    mapToFlossColor(dmc, squareWidth, numOfSuperPixelsRows, squareHeight, squares, legendMap,
            builder);
    builder.append("\n").append("Legend").append("\n");
    createLegendMap(legendMap, builder);

    return builder.toString();
  }

  /**
   * Maps the legend symbols to their respective DMC values.
   *
   * @param legendMap the hashMap that contains the values.
   * @param builder to build outPut to be returned.
   */
  private void createLegendMap(Map<String, String> legendMap, StringBuilder builder) {
    for (String legends : legendMap.keySet()) {
      builder.append(legends).append(legendMap.get(legends)).append("\n");
    }
  }

  /**
   * Maps a legend to a floss color and stores output in a string builder.
   *
   * @param dmc the DMC values table.
   * @param squareWidth the width of the square Super pixel.
   * @param numOfSuperPixelsRows the number of super pixel rows.
   * @param squareHeight the height of the square super pixel.
   * @param legendMap the legend map.
   * @param builder the builder that stores te output.
   */
  private void mapToFlossColor(
      String[][] dmc,
      float squareWidth,
      int numOfSuperPixelsRows,
      float squareHeight,
      int squares,
      Map<String, String> legendMap,
      StringBuilder builder)

  {

    int[] superChannels = new int[3];
    int[] dmcChannels = new int[3];
    for (int superRow = 0; superRow < numOfSuperPixelsRows; superRow++) {
      for (int superCol = 0; superCol < squares; superCol++) {
//        List<int[]> pixels = new ArrayList<>();
//        getAllPixels(this.image, numOfSuperPixelsRows,squares,squareHeight,squareWidth,pixels);


        getSuperPixelColor(squareWidth, squareHeight, superChannels, superRow, superCol);
        double distance = Double.MAX_VALUE;
        int dmcIndex = getDmcIndex(dmc, superChannels, dmcChannels, distance);

        builder.append(dmc[dmcIndex][1]).append(" ");
        int[] mc = new int[3];
        mc[0] = 255;
        mc[1] =255;
        mc[2] = 255;
//        replacePixelValue(pixels,mc);
        legendMap.put(dmc[dmcIndex][1] + " ", "DMC-" + dmc[dmcIndex][0]);
      }
      builder.append("\n");
    }
  }

  @Override
  public int[][][] getImagePattern() {
    return image;
  }

  protected void getAllPixels(
          int[][][] image,
          int superRow,
          int superCol,
          float squareHeight,
          float squareWidth,
          List<int[]> pixels) {
    for (int pixelRow = Math.round(superRow * squareHeight);
         pixelRow < (superRow + 1) * squareHeight;
         pixelRow++) {
      for (int pixelCol = Math.round(superCol * squareWidth);
           pixelCol < (superCol + 1) * squareWidth;
           pixelCol++) {
        System.out.println(pixelRow);
        if (pixelRow < this.image.length) {
          System.out.println("im running yayyy");
          pixels.add(image[pixelRow][pixelCol]);

        }

      }
    }
  }


  protected void replacePixelValue(List<int[]> pixels, int[] channels) {
    for (int[] pixel : pixels) {
      pixel[0] = channels[0];
      pixel[1] = channels[1];
      pixel[2] = channels[2];
    }
  }

  /**
   * Gets the channel of a Super Pixel.
   *
   * @param squareWidth the width of the square.
   * @param squareHeight the height of the square.
   * @param superChannels the channel of the super pixel.
   * @param superRow the row of the superPixel.
   * @param superCol the col of the superPixel.
   */
  private void getSuperPixelColor(
          float squareWidth, float squareHeight, int[] superChannels, int superRow, int superCol) {
    superChannels[0] =
            image[Math.round(superRow * squareHeight)][Math.round(superCol * squareWidth)][0];
    superChannels[1] =
            image[Math.round(superRow * squareHeight)][Math.round(superCol * squareWidth)][1];
    superChannels[2] =
            image[Math.round(superRow * squareHeight)][Math.round(superCol * squareWidth)][2];
  }

  /**
   * Gets the index of the DMC value that's the closest to the super pixel.
   *
   * @param dmc the dmc value array.
   * @param superChannels the channel of the super pixel.
   * @param dmcChannels the channel of DMC values.
   * @param distance the distance between dmc and super pixel channels
   * @return the index.
   */
  private int getDmcIndex(String[][] dmc, int[] superChannels, int[] dmcChannels, double distance) {
    int dmcIndex = 0;
    for (int row = 0; row < dmc.length; row++) {
      dmcChannels[0] = Integer.parseInt(dmc[row][2]);
      dmcChannels[1] = Integer.parseInt(dmc[row][3]);
      dmcChannels[2] = Integer.parseInt(dmc[row][4]);
      if (colourDistance(superChannels, dmcChannels) < distance) {
        distance = colourDistance(superChannels, dmcChannels);
        dmcIndex = row;
      }
    }
    return dmcIndex;
  }



  /**
   * Calculates the distance between two colors.
   *
   * @param superChannel the first color.
   * @param dmcChannel the second color.
   * @return the distance as a double.
   */
  private double colourDistance(int[] superChannel, int[] dmcChannel) {
    long rmean = ((long) superChannel[0] + (long) dmcChannel[0]) / 2;
    long redChange = (long) superChannel[0] - (long) dmcChannel[0];
    long greenChange = (long) superChannel[1] - (long) dmcChannel[1];
    long blueChange = (long) superChannel[2] - (long) dmcChannel[2];
    return Math.sqrt(
        (((2 + (rmean / 256.0)) * redChange * redChange))
            + 4 * greenChange * greenChange
            + (((2 + ((255 - rmean) / 256.0)) * blueChange * blueChange)));
  }

  /**
   * Replaces a column in the  DMC values array.
   *
   * @param dmc the array whose column is to be replaced.
   * @param columnNumber the column number to be replaced.
   * @param legend the list that contains the new column values.
   */
  private void replaceColumn(String[][] dmc, int columnNumber, List<String> legend) {
    for (int row = 0; row < dmc.length; row++) {
      dmc[row][columnNumber] = legend.get(row);
    }
  }

  /**
   * Builds a list of legends which is all two letter combination of the alphabets.
   *
   * @return a list containing the alphabet combination.
   */
  private List<String> getLegend() {
    List<String> legend = new ArrayList<String>();

    for (int i = 97; i <= 122; i++) {
      for (int j = 97; j <= 122; j++) {
        legend.add((char) i + String.valueOf((char) j));
      }
    }
    return legend;
  }
}

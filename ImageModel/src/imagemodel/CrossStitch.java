package imagemodel;

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
  private final HashMap<String, String> legendMap;
  private String legendText;
  private String[][] dmc;

  /**
   * Default Constructor.
   *
   * @param image image to be generated into a cross stitch pattern.
   */
  public CrossStitch(int[][][] image) {
    super(image);
    crossedImage = new int[0][][];
    legendMap = new HashMap<>();
    dmc = new Dmc().getDmcColors();
    legendText = "";
  }

  @Override
  public String generate() {
    crossedImage = deepCopy(image);
    Chunking pixelate = new Pixelation(crossedImage);
    int squares = 100;
    crossedImage = pixelate.apply(squares);
    float squareWidth = (float) crossedImage[0].length / squares;
    int numOfSuperPixelsRows = Math.round((float) crossedImage.length / squareWidth);
    float squareHeight = (float) crossedImage.length / numOfSuperPixelsRows;

    StringBuilder builder = new StringBuilder();
    mapToFlossColor(dmc, squareWidth, numOfSuperPixelsRows, squareHeight, squares, legendMap,
            builder);
    builder.append("\n").append("Legend").append("\n");
    createLegendMap(legendMap, builder);

    return builder.toString();
  }

  @Override
  public void setDmc(Dmc dmcToSet) {
    this.dmc = dmcToSet.getDmcColors();
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

  @Override
  public List<String> getUsedColors() {
    List<String> usedColors = new ArrayList<>();
    for (String legends: legendMap.keySet()) {
      usedColors.add(legendMap.get(legends));
    }

    return usedColors;
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
    StringBuilder builder1 = new StringBuilder();
    for (int superRow = 0; superRow < numOfSuperPixelsRows; superRow++) {
      for (int superCol = 0; superCol < squares; superCol++) {
        int imageRow = Math.round(superRow * squareHeight);
        int imageCol = Math.round(superCol * squareWidth);
        getSuperPixelColor(superChannels,imageRow, imageCol);
        double distance = Double.MAX_VALUE;
        int dmcIndex = getDmcIndex(dmc, superChannels, dmcChannels, distance, imageRow, imageCol, superRow, squareHeight,squareWidth,superCol);

        builder.append(dmc[dmcIndex][1]).append(" ");
        builder1.append(dmc[dmcIndex][1]).append(" ");
        legendMap.put(dmc[dmcIndex][1] + " ", "DMC " + dmc[dmcIndex][0]);
      }
      builder.append("\n");
    }
    legendText = builder1.toString();
  }

  @Override
  public int[][][] getImagePattern() {
    return deepCopy(crossedImage);
  }

  @Override
  public String getStringIcon() {
    return new String(legendText.toString());
  }


  /**
   * Gets the channel of a Super Pixel.
   *
   * @param superChannels the channel of the super pixel.
   */
  private void getSuperPixelColor(
          int[] superChannels,int imageRow, int imageCol) {
    superChannels[0] =
            crossedImage[imageRow][imageCol][0];
    superChannels[1] =
            crossedImage[imageRow][imageCol][1];
    superChannels[2] =
            crossedImage[imageRow][imageCol][2];
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
  private int getDmcIndex(String[][] dmc, int[] superChannels, int[] dmcChannels, double distance, int imageRow, int imageCol, int superRow, float squareHeight, float squareWidth, int superCol ) {
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
    for (int i = imageRow; i < (superRow + 1) * squareHeight; i++)
      for (int j = imageCol; j < (superCol + 1) * squareWidth; j++) {
        crossedImage[i][j][0] = Integer.parseInt(dmc[dmcIndex][2]);
        crossedImage[i][j][1] = Integer.parseInt(dmc[dmcIndex][3]);
        crossedImage[i][j][2] = Integer.parseInt(dmc[dmcIndex][4]);
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


}

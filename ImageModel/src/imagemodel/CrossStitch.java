package imagemodel;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Implements the Chunking interface and represents a class which applies the Pixelation operation
 * to an image.
 */
public class CrossStitch extends AbstractImageModel implements Pattern {

  public CrossStitch(int[][][] image) {
    super(image);
  }

  public static void main(String[] args) {
//    File csv = new File("");
//    String path = csv.getAbsolutePath() + "\\DMC Cotton Floss converted to RGB Values.csv";
//    String[][] dmc = new String[0][];
//    try {
//      dmc = FileUtilities.loadCsvFile(path);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    CrossStitch crossStitch = new CrossStitch(null);
//    List<String> legend = crossStitch.getLegend();
//
//    crossStitch.replaceColumn(dmc, 1, legend);
//    System.out.println(Arrays.deepToString(dmc));
  }

  @Override
  public void generate() {
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
    pixelate.apply(50);
    float squareWidth = (float) image[0].length / 50;
    int numOfSuperPixelsRows = Math.round((float) image.length / squareWidth);
    float squareHeight = (float) image.length / numOfSuperPixelsRows;
    int[] superChannels = new int[3];
    int[] dmcChannels = new int[3];
    double distance = Double.MAX_VALUE;
    int dmcIndex = 0;
    Map<String,String> legendMap = new HashMap<>();
    StringBuilder builder = new StringBuilder();
    builder.append(numOfSuperPixelsRows).append(" x ").append(50).append("\n");

    for (int superRow = 0; superRow < numOfSuperPixelsRows; superRow++) {
      for (int superCol = 0; superCol < 50; superCol++) {

        superChannels[0] =
            image[Math.round(superRow * squareHeight)][Math.round(superCol * squareWidth)][0];
        superChannels[1] =
            image[Math.round(superRow * squareHeight)][Math.round(superCol * squareWidth)][1];
        superChannels[2] =
            image[Math.round(superRow * squareHeight)][Math.round(superCol * squareWidth)][2];

        for (int row = 0; row < dmc.length; row++) {
            dmcChannels[0] = Integer.parseInt(dmc[row][2]);
            dmcChannels[1] = Integer.parseInt(dmc[row][3]);
            dmcChannels[2] = Integer.parseInt(dmc[row][4]);
            if (ColourDistance(superChannels, dmcChannels) < distance) {
              distance = ColourDistance(superChannels, dmcChannels);
              dmcIndex = row;
            }
        }

        builder.append(dmc[dmcIndex][1]).append(" ");
        legendMap.put(dmc[dmcIndex][1] + " ", "DMC-" + dmc[dmcIndex][0]);

      }
      builder.append("\n");
    }
    builder.append("\n").append("Legend").append("\n");

    for (String legends: legendMap.keySet()) {
      builder.append(legends).append(legendMap.get(legends)).append("\n");
    }

    FileUtilities.writeToFile("crossStitch.txt",builder.toString());


  }

  private double ColourDistance(int[] superChannel, int[] dmcChannel) {
    long rmean = ((long) superChannel[0] + (long) dmcChannel[0]) / 2;
    long rChange = (long) superChannel[0] - (long) dmcChannel[0];
    long gChange = (long) superChannel[1] - (long) dmcChannel[1];
    long bChange = (long) superChannel[2] - (long) dmcChannel[2];
    return Math.sqrt(
        (((2 + (rmean / 256.0)) * rChange * rChange))
            + 4 * gChange * gChange
            + (((2 + ((255 - rmean) / 256.0)) * bChange * bChange)));
  }

  /**
   * Replaces a column in a
   * @param dmc
   * @param columnNumber
   * @param legend
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

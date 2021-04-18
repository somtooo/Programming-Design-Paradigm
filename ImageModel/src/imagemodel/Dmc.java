package imagemodel;

import imagemodel.utilities.FileUtilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Represents a class that performs operations using DMC colors
 */
public class Dmc  {

    private  String[][] dmcColors;
    private  Map<String, int[]> dmcToRgb;
    private Map<String, String> legendToDmc;
    private Map<String, String[]> dmcMap;

    /**
     * Default constructor.
     */
    public Dmc() {
        File csv = new File("");
        String path = csv.getAbsolutePath() + "\\DMC Cotton Floss converted to RGB Values.csv";
        try {
            dmcColors = FileUtilities.loadCsvFile(path);
            List<String> legend = getLegend();
            replaceColumn(dmcColors, legend);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dmcToRgb = new HashMap<>();
        dmcToRgb = buildDmcMap();
        legendToDmc = new HashMap<>();
        legendToDmc = buildLegendMap();

    }

    /**
     * Gets the dmc floss values.
     * @return an array that has all the dmc floss values.
     */
    public String[] getDmcFlossValues() {
        String[][] copyDmc = deepCopy(dmcColors);
        String[] floss = new String[copyDmc.length];

        for(int i = 0; i < copyDmc.length; i++) {
            floss[i] = "DMC " + copyDmc[i][0];
        }
        return floss;
    }


    /*
    Gets the dmc color array.
     */
    public String[][] getDmcColors() {
        return deepCopy(dmcColors);
    }



    /**
     * Builds a mapping between the floss code and rgb code for easy search.
     * @return hashMap with relationship.
     */
    private Map<String, int[]> buildDmcMap() {
        Map<String, int[]> values = new HashMap<>();
        for (int i = 0; i < dmcColors.length; i++) {
            values.put("DMC " + dmcColors[i][0], new int[]{Integer.parseInt(dmcColors[i][2]),
                    Integer.parseInt(dmcColors[i][3]), Integer.parseInt(dmcColors[i][4])});
        }

        return values;

    }

    /**
     * Gets the dmc code value from an rgb value.
     * @param rgb the rgb value to convert to dmc value.
     * @return the dmc code value.
     */
    public String getDmcCodeFromRgb(int[] rgb) {
        AtomicReference<String> answer = new AtomicReference<>(" kk");
        System.out.println(Arrays.toString(rgb));

        dmcToRgb.forEach((key, value) -> {
           if (Arrays.equals(value, rgb)) {
               answer.set(key);
           }
        });
        return answer.get();
    }

  public static <ImageProcessor> void main(String[] args) throws IOException {
      BufferedImage image = ImageIO.read(new File("C:\\Users\\chukw\\IdeaProjects\\ImageModel\\goat.png"));

      Font font = new Font("Arial", Font.BOLD, 18);

      Graphics g = image.getGraphics();
      g.setFont(font);
      g.setColor(Color.GREEN);
      g.drawString("A", 0, 0);
  }

    /**
     * Builds a mapping between the floss code and legend icon for easy search.
     * @return hashMap with relationship.
     */
    private Map<String, String> buildLegendMap() {
        Map<String, String> values = new HashMap<>();
        for (int i = 0; i < dmcColors.length; i++) {
            values.put("DMC " + dmcColors[i][0], dmcColors[i][1]);
        }

        return values;

    }


//    /**
//     * Builds a mapping between the floss code and its various information for fast search.
//     * @return hashMap with relationship.
//     */
//    private Map<String, String[]> buildDmcColorMap() {
//        Map<String, String[]> values = new HashMap<>();
//        System.out.println(dmcColors[0].length);
//        for (int i = 0; i < dmcColors.length; i++) {
//            values.put("DMC " + dmcColors[i][0], new String[]{dmcColors[i][0], dmcColors[i][1],dmcColors[i][2],dmcColors[i][3],dmcColors[i][4],dmcColors[i][5],dmcColors[i][6]});
//        }
//        return values;
//    }

    /**
     * Gets the icon linked with the floss value.
     * @param dmcFlossValue the floss value to use to get icon.
     * @return the icon as a String.
     */
    public String getIcon(String dmcFlossValue) {
        return legendToDmc.get(dmcFlossValue);
    }


    /**
     * Sets the preferred dmc colors to be used.
     * @param colorsToSet the list of colors preferred to be used.
     */
    public void setDmcColors(List<String> colorsToSet) {
        String[][] dmcToSet = new String[colorsToSet.size()][dmcColors[0].length];
        String[] copyColorsToSet = new String[colorsToSet.size()];
        copyColorsToSet = colorsToSet.toArray(copyColorsToSet);

        int i = 0;
        for (String value : copyColorsToSet ) {
            for (int row = 0; row < dmcColors.length; row++) {
                    if (value.equals("DMC " + dmcColors[row][0])) {
                        dmcToSet[i] = dmcColors[row];
                        i++;
                    }
                }
        }
        dmcColors = dmcToSet;
    }



    /**
     * Gets the rgb code of when provided a dmc floss value.
     * @param dmcFlossValue the dmc value provided.
     * @return the rgb code of the dmc value.
     */
    public int[] getRgb(String dmcFlossValue) {
        return dmcToRgb.get(dmcFlossValue).clone();
    }


    /**
     * Makes a deep copy of a 2d-Array;
     * @param matrix the array to make copy of.
     * @param <T> the type of array.
     * @return the deep copy of the array.
     */
    private <T> T[][] deepCopy(T[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
    }

    /**
     * Replaces a column in the  DMC values array.
     *  @param dmc the array whose column is to be replaced.
     * @param legend the list that contains the new column values.
     */
    private void replaceColumn(String[][] dmc, List<String> legend) {
        for (int row = 0; row < dmc.length; row++) {
            dmc[row][1] = legend.get(row);
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

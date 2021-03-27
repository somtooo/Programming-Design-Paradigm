package imagemodel.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** File utility class that has methods to read a file and write to a file. */
public class FileUtilities {

  /**
   * Reads content from a csv file and returns as a 2d array.
   *
   * @param path the path to the csv file.
   * @return a 2d array that has the contents of the file.
   */
  public static String[][] loadCsvFile(String path) throws IOException {
    if (path == null) {
      throw new IllegalArgumentException("no nulls allowed");
    }
    String line;
    List<String[]> values = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(path));
    while ((line = br.readLine()) != null) {
      values.add(line.split(","));
    }
    values = values.subList(1, values.size());
    String[][] array = new String[values.size()][0];

    values.toArray(array);
    return array;
  }

  /**
   * Writes a string value to a file.
   *
   * @param filename the filename of the file.
   * @param content the string content to be written.
   * @throws IOException if something goes wrong with writing to file.
   */
  public static void writeToFile(String filename, String content) throws IOException {
    if (filename == null | content == null) {
      throw new IllegalArgumentException("no nulls allowed");
    }

    FileWriter myWriter = new FileWriter(filename);
    myWriter.write(content);
    myWriter.close();
  }
}

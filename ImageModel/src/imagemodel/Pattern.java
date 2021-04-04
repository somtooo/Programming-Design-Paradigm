package imagemodel;

import java.io.IOException;

/** Represents operations that generates various embroidery patterns from images. */
public interface Pattern {

  /**
   * Generates an embroidery pattern from an image
   * @return a string that represents the pattern.
   * @throws IOException if the patterns csv file isn't read correctly
   */
  String generate() throws IOException;
}

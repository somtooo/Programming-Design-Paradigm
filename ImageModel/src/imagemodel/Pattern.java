package imagemodel;

import java.util.List;

/** Represents operations that generates various embroidery patterns from images. */
public interface Pattern {

  /** Generates an embroidery pattern from an image. */
  String generate();

    void setDmc(Dmc dmcToSet);

    List<String> getUsedColors();

    int[][][] getImagePattern();

    String getStringIcon();
}

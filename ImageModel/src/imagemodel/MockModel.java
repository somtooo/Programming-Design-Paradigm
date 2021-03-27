package imagemodel;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * This represents a mock of the Image model. The operations here do not really interact with the
 * controller but is used to test that the controller receives input and output correctly.
 */
public class MockModel implements ImageModelInterface {
  private final StringBuilder log;

  /**
   * Default constructor.
   *
   * @param log saves the inputs to the model.
   */
  public MockModel(StringBuilder log) {
    if (log == null) {
      throw new IllegalArgumentException("no null allowed");
    }
    this.log = log;
  }

  @Override
  public void blur(int intensity, int[][][] image) throws IllegalStateException {
    Objects.requireNonNull(image, "image cannot  be null");
    log.append(intensity).append(" ").append(Arrays.deepToString(image));
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
  }

  @Override
  public void sharpen(int intensity, int[][][] image) throws IllegalStateException {
    Objects.requireNonNull(image, "image cannot  be null");
    log.append(intensity).append(" ").append(Arrays.deepToString(image));
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
  }

  @Override
  public void greyScale(int[][][] image) throws IllegalStateException {
    Objects.requireNonNull(image, "image cannot  be null");
    log.append(Arrays.deepToString(image));
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
  }

  @Override
  public void sepia(int[][][] image) throws IllegalStateException {
    Objects.requireNonNull(image, "image cannot  be null");
    log.append(Arrays.deepToString(image));
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
  }

  @Override
  public void reduceColor(int numberOfColors, int[][][] image) throws IllegalStateException {
    Objects.requireNonNull(image, "image cannot  be null");
    log.append(numberOfColors).append(" ").append(Arrays.deepToString(image));

    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
  }

  @Override
  public void toMosaic(int seeds, int[][][] image) throws IllegalStateException {
    Objects.requireNonNull(image, "image cannot  be null");
    log.append(seeds).append(" ").append(Arrays.deepToString(image));

    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
  }

  @Override
  public void pixelate(int squares, int[][][] image) throws IllegalStateException {
    Objects.requireNonNull(image, "image cannot  be null");
    log.append(squares).append(" ").append(Arrays.deepToString(image));

    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
  }

  @Override
  public void crossStitch(int[][][] image) throws IllegalStateException {
    Objects.requireNonNull(image, "image cannot  be null");
    log.append(Arrays.deepToString(image));

    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
  }

  @Override
  public int[][][] loadImage(String filename) throws IOException {
    Objects.requireNonNull(filename);
    log.append(filename).append(" ");
    if (filename.equals("fail.jpg")) {
      throw new IOException("Cant load file");
    }
    return new int[][][] {
      {
        {1, -2, 3},
        {2, 3, 4}
      },
      {
        {-4, -5, 6, 9},
        {1},
        {2, 3}
      }
    };
  }

  @Override
  public void saveImage(String filename, int[][][] image)
      throws IllegalStateException, IOException {
    Objects.requireNonNull(image, "image cannot  be null");
    Objects.requireNonNull(filename);
    log.append(filename).append(" ").append(Arrays.deepToString(image));
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
    if (filename.equals("fail.jpg")) {
      throw new IOException(" cant save image");
    }
  }

  @Override
  public void savePattern(String filename) throws IOException {
    Objects.requireNonNull(filename);
    log.append(filename).append(" ");
    if (filename.equals("fail.txt")) {
      throw new IOException("Cant save file");
    }
  }
}

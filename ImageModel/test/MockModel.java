import imagemodel.ImageModelInterface;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * This represents a mock of the Image model. The operations here do not really interact with the
 * controller but is used to test that the controller receives input and output correctly.
 */
public class MockModel implements ImageModelInterface {
  private final StringBuilder log;
  private int[][][] image;

  /**
   * Default constructor.
   *
   * @param log saves the inputs to the model.
   */
  public MockModel(StringBuilder log) {
    if (log == null) {
      throw new IllegalArgumentException("no null allowed");
    }
    image = new int[0][][];
    this.log = log;
  }

  @Override
  public int[][][] blur(int intensity) throws IllegalStateException {
    log.append(intensity);
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
    return new int[1][][];
  }

  @Override
  public int[][][] sharpen(int intensity) throws IllegalStateException {
    log.append(intensity);
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
    return new int[1][][];
  }

  @Override
  public int[][][] greyScale() throws IllegalStateException {
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
    return new int[1][][];
  }

  @Override
  public int[][][] sepia() throws IllegalStateException {
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
    return new int[1][][];
  }

  @Override
  public int[][][] reduceColor(int numberOfColors) throws IllegalStateException {
    log.append(numberOfColors);

    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
    return new int[1][][];
  }

  @Override
  public int[][][] toMosaic(int seeds) throws IllegalStateException {
    log.append(seeds);

    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
    return new int[1][][];
  }

  @Override
  public int[][][] pixelate(int squares) throws IllegalStateException {
    log.append(squares);

    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
    return new int[1][][];
  }

  @Override
  public void crossStitch() throws IllegalStateException {
    if (image.length == 0) {
      throw new IllegalStateException("Image is empty");
    }
  }

  @Override
  public void loadImage(String filename) throws IOException {
    Objects.requireNonNull(filename);
    log.append(filename);
    if (filename.equals("fail.jpg")) {
      throw new IOException("Cant load file");
    }
    image = new int[1][][];

  }

  @Override
  public void saveImage(String filename)
      throws IllegalStateException, IOException {
    Objects.requireNonNull(filename);
    log.append(filename);
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
    log.append(filename);
    if (filename.equals("fail.txt")) {
      throw new IOException("Cant save file");
    }
  }

  @Override
  public void getImagePattern() {

  }

  @Override
  public BufferedImage getBufferedImage() {
    return null;
  }
}

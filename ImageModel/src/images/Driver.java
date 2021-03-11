package images;

import imagemodel.Blur;
import imagemodel.FloydDithering;
import imagemodel.GreyScale;
import imagemodel.ImageModel;
import imagemodel.Sepia;
import imagemodel.Sharpen;

import java.io.File;
import java.io.IOException;

/** Represents functionality that shows the results of manipulated images. */
public class Driver {

  /**
   * Manipulates images to show some interesting effects.
   *
   * @param args as input from the user.
   * @throws IOException if the image path is not valid.
   */
  public static void main(String[] args) throws IOException {
    File file = new File("");
    String next = file.getAbsolutePath() + "\\dog2.jpg";
    int[][][] imageToBlur = ImageUtilities.readImage(next);
    int[][][] imageToSharpen = ImageUtilities.readImage(next);
    int[][][] imageToSepia = ImageUtilities.readImage(next);
    int[][][] imageToGrey = ImageUtilities.readImage(next);
    int[][][] reduceColor = ImageUtilities.readImage(next);

    ImageModel blur = new Blur();
    ImageModel sharpen = new Sharpen();
    ImageModel sepia = new Sepia();
    ImageModel grey = new GreyScale();
    ImageModel reduce = new FloydDithering();

    ImageUtilities.writeImage(
        blur.apply(imageToBlur, 1),
        imageToBlur[0].length,
        imageToBlur.length,
        file.getAbsolutePath() + "blurredImageFromJar.jpg");
    System.out.println("Writing blurred image to project folder");
    ImageUtilities.writeImage(
        sharpen.apply(imageToSharpen, 1),
        imageToSharpen[0].length,
        imageToSharpen.length,
        file.getAbsolutePath() + "sharpenedImageFromJar.jpg");
    System.out.println("Writing sharpened image to project folder");
    ImageUtilities.writeImage(
        sepia.apply(imageToSepia, 1),
        imageToSepia[0].length,
        imageToSepia.length,
        file.getAbsolutePath() + "sepiaImageFromJar.jpg");
    System.out.println("Writing sepia image to project folder");
    ImageUtilities.writeImage(
        grey.apply(imageToGrey, 1),
        imageToGrey[0].length,
        imageToGrey.length,
        file.getAbsolutePath() + "greyImageFromJar.jpg");
    System.out.println("Writing grey image to project folder");
    ImageUtilities.writeImage(
        reduce.apply(reduceColor, 2),
        reduceColor[0].length,
        reduceColor.length,
        file.getAbsolutePath() + "ditheredImageFromJar.jpg");
    System.out.println("Writing reduced color image to project folder");
  }
}

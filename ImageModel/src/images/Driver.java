package images;

import imagemodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/** Represents functionality that shows the results of manipulated images. */
public class Driver {

  /**
   * Manipulates images to show some interesting effects.
   *
   * @param args as input from the user.
   * @throws IOException if the image path is not valid.
   */
  public static void main(String[] args) throws IOException {
    //    int[][][] image =
    //
    // ImageUtilities.readImage("C:\\Users\\chukw\\IdeaProjects\\ImageModel\\src\\Capture.PNG");
    //    System.out.println(image.length);
    //    System.out.println(image[0].length);
    //
    //    Chunking cross = new Pixelation(image);
    //
    //    int[][][] newImage = cross.apply(119);
    //    ImageUtilities.writeImage( newImage, newImage[0].length, newImage.length, "mosaic.PNG");

    String str = "goat.png";
    int[][][] arrOfStr = new int[0][][];

    System.out.println(arrOfStr.length);

    //    File file = new File("");
    //    String next = file.getAbsolutePath() + "\\dog2.jpg";
    //    int[][][] imageToBlur = ImageUtilities.readImage(next);
    //    int[][][] imageToSharpen = ImageUtilities.readImage(next);
    //    int[][][] imageToSepia = ImageUtilities.readImage(next);
    //    int[][][] imageToGrey = ImageUtilities.readImage(next);
    //    int[][][] reduceColor = ImageUtilities.readImage(next);
    //
    //    Filter blur = new Blur();
    //    Filter sharpen = new Sharpen();
    //    Transform sepia = new Sepia();
    //    Transform grey = new GreyScale();
    //    Reduce reduce = new FloydDithering();
    //
    //    ImageUtilities.writeImage(
    //        blur.applyFilter(imageToBlur, 1),
    //        imageToBlur[0].length,
    //        imageToBlur.length,
    //        file.getAbsolutePath() + "blurredImageFromJar.jpg");
    //    System.out.println("Writing blurred image to project folder");
    //    ImageUtilities.writeImage(
    //        sharpen.applyFilter(imageToSharpen, 1),
    //        imageToSharpen[0].length,
    //        imageToSharpen.length,
    //        file.getAbsolutePath() + "sharpenedImageFromJar.jpg");
    //    System.out.println("Writing sharpened image to project folder");
    //    ImageUtilities.writeImage(
    //        sepia.applyTransform(imageToSepia),
    //        imageToSepia[0].length,
    //        imageToSepia.length,
    //        file.getAbsolutePath() + "sepiaImageFromJar.jpg");
    //    System.out.println("Writing sepia image to project folder");
    //    ImageUtilities.writeImage(
    //        grey.applyTransform(imageToGrey),
    //        imageToGrey[0].length,
    //        imageToGrey.length,
    //        file.getAbsolutePath() + "greyImageFromJar.jpg");
    //    System.out.println("Writing grey image to project folder");
    //    ImageUtilities.writeImage(
    //        reduce.reduce(reduceColor, 2),
    //        reduceColor[0].length,
    //        reduceColor.length,
    //        file.getAbsolutePath() + "ditheredImageFromJar.jpg");
    //    System.out.println("Writing reduced color image to project folder");
  }
  }


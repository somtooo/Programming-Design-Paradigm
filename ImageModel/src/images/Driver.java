package images;


import imagemodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Driver {

  public static void main(String[] args) throws IOException {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter image full path...");
      String next = sc.next();
      System.out.println(next);
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

      ImageUtilities.writeImage(blur.apply(imageToBlur,1), imageToBlur[0].length,imageToBlur.length,"blurredImage.jpg");
      ImageUtilities.writeImage(sharpen.apply(imageToSharpen,1), imageToSharpen[0].length,imageToSharpen.length,"sharpenedImage.jpg");
      ImageUtilities.writeImage(sepia.apply(imageToSepia,1), imageToSepia[0].length,imageToSepia.length,"sepiaImage.jpg");
      ImageUtilities.writeImage(grey.apply(imageToGrey,1), imageToGrey[0].length,imageToGrey.length,"greyImage.jpg");
      ImageUtilities.writeImage(reduce.apply(reduceColor,8), reduceColor[0].length,reduceColor.length,"ditheredImage.jpg");


  }


}

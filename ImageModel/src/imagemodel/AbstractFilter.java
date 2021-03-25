package imagemodel;

/** Represents calculations used to perform filtering operations in an image. */
public abstract class AbstractFilter extends AbstractImageModel {

  public AbstractFilter(int[][][] image) {
    super(image);
  }

  /**
   * Performs the calculation to replace the images RGB.
   *
   * @param image the image whose RGB values are to be replaced.
   * @param kernel 2D array used in the calculation.
   */
  protected void convolve(int[][][] image, float[][] kernel) {
    int offset = kernel.length / 2;

    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[row].length; col++) {
        int red = 0;
        int green = 0;
        int blue = 0;

        performConvolutionCalculation(image, kernel, offset, row, col, red, green, blue);
      }
    }
    clamp(image);
  }

  /**
   * Takes a pixel and determines the new values of the pixel depending on the values of its
   * neighbours.
   *
   * @param image the image array to perform the calculation on.
   * @param kernel the 2d array whose values are used in the calculation.
   * @param offset the center of the kernel.
   * @param row the current row index of the image array.
   * @param col the current column index of the image array.
   * @param red the red color value.
   * @param green the green color value.
   * @param blue the blue color value.
   * @throws IllegalArgumentException if the image or kernel is null.
   */
  private void performConvolutionCalculation(
      int[][][] image,
      float[][] kernel,
      int offset,
      int row,
      int col,
      int red,
      int green,
      int blue) {
    if (image == null | kernel == null) {
      throw new IllegalArgumentException("Null not allowed");
    }
    for (int blurRow = 0; blurRow < kernel.length; blurRow++) {
      for (int blurCol = 0; blurCol < kernel[blurRow].length; blurCol++) {

        int relatedPixelRow = row + blurRow - offset;
        int relatedPixelCol = col + blurCol - offset;

        if (!(relatedPixelRow < 0 || relatedPixelRow >= image.length)
            && !(relatedPixelCol < 0 || relatedPixelCol >= image[row].length)) {
          float currentBlurKernelValue = kernel[blurRow][blurCol];
          red += (int) (currentBlurKernelValue * image[relatedPixelRow][relatedPixelCol][0]);
          green += (int) (currentBlurKernelValue * image[relatedPixelRow][relatedPixelCol][1]);
          blue += (int) (currentBlurKernelValue * image[relatedPixelRow][relatedPixelCol][2]);
        }
      }
    }
    image[row][col][0] = red;
    image[row][col][1] = green;
    image[row][col][2] = blue;
  }
}

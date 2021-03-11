package imagemodel;

/** Represents calculations used to transform colors in an image. */
public abstract class AbstractColorTransformation extends AbstractImageModel {

  /**
   * Replaces the RGB values of an image by applying linear color transformation using the image and
   * a kernel.
   *
   * @param image the image whose RGB values are to be replaced.
   * @param kernel the 2d array used in the calculation.
   * @throws IllegalArgumentException if the image or kernel is null.
   */
  protected void colorTransformation(int[][][] image, float[][] kernel) {
    for (int row = 0; row < image.length; row++) {
      for (int col = 0; col < image[row].length; col++) {

        image[row][col] = linearColorTransformation(kernel, image[row][col]);
      }
    }
    clamp(image);
  }

  /**
   * Multiplies an array and a 2d array together using the linear transformation formula.
   *
   * @param kernel the 2d array used in the calculation.
   * @param color the array used in the calculation.
   * @return an array which contains the result of the calculation.
   */
  private int[] linearColorTransformation(float[][] kernel, int[] color) {
    if (kernel.length != color.length) {
      throw new IllegalArgumentException("Multiplication is not possible");
    }

    int[] answer = new int[color.length];

    for (int kIndex = 0; kIndex < kernel.length; kIndex++) {
      for (int cRow = 0; cRow < color.length; cRow++) {
        answer[kIndex] += (kernel[kIndex][cRow] * color[cRow]);
      }
    }
    return answer;
  }
}

package imagemodel;
/** Represents color reduction operations that can be performed on an image. */

public interface Reduce {
    /**
     * Applies a color reduction operation on an image.
     *
     * @param image the image that the operation will be applied on.
     * @throws IllegalArgumentException if image is null.
     * @throws IllegalArgumentException if intensity is negative or 0.
     */
    int[][][] reduce(int[][][] image, int numOfColors);
}

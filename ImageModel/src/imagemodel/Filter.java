package imagemodel;
/** Represents filtering operations that can be performed on an image. */
public interface Filter {

    /**
     * Applies filtering operations on an image.
     *
     * @param image the image that the operation will be applied on.
     * @param intensity how many times do you want to apply this filter operation.
     * @throws IllegalArgumentException if intensity is negative or 0.
     * @throws IllegalArgumentException if image is null.
     */
    int[][][] applyFilter(int[][][] image, int intensity);
}

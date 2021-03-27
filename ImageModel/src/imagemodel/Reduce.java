package imagemodel;

/** Represents color reduction operations that can be performed on an image. */
public interface Reduce {
    /**
     * Applies a color reduction operation on an image.
     *
     * @throws IllegalArgumentException if numOfColors is negative or 0.
     */
    int[][][] reduce(int numOfColors)throws IllegalArgumentException;
}

package imagemodel;

/** Represents chunking operations that can be performed on an image. */

public interface Chunking {

    /**
     * Applies a chunking operation on an image.
     *
     * @param value refer to class documentation for definition.
     * @return a 2d array that represents the image.
     * @throws IllegalArgumentException if value is negative or 0.
     */
    int[][][] apply(int value) throws IllegalArgumentException;
}

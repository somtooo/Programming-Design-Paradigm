package imagemodel;

/** Represents chunking operations that can be performed on an image. */

public interface Chunking {

    /**
     * Applies a chunking operation on an image.
     *
     * @param value refer to class documentation for definition.
     * @throws IllegalArgumentException if value is negative or 0.
     */
    int[][][] apply(int value) throws IllegalArgumentException;
}

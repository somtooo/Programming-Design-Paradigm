package imagemodel;
/** Represents chunking operations that can be performed on an image. */

public interface Chunking {

    /**
     * Applies a chunking operation on an image.
     *
     * @param image the image that the operation will be applied on.
     * @param value refer to class documentation for definition.
     * @throws IllegalArgumentException if value is negative or 0.
     * @throws IllegalArgumentException if image is null.
     */
    int[][][] apply(int value);
}

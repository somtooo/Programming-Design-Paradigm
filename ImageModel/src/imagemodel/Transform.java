package imagemodel;
/** Represents transformation operations that can be performed on an image. */
public interface Transform {
    /**
     * Applies a transformation operation on an image.
     *
     * @throws IllegalArgumentException if image is null.
     */
    int[][][] applyTransform();
}

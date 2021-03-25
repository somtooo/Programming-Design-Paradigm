package imagemodel;

/**
 * Represents operations that generates various embroidery patterns from images.
 */
public interface Pattern {

    /**
     * Generates an embroidery pattern from an image.
     * @param image the image the pattern is generated from.
     */
    void generate(int[][][] image);
}

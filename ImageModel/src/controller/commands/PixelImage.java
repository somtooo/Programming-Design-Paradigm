package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;





/**
 * This command runs the pixelate image operation.
 */
public class PixelImage implements ImageCommand {
    private final int numberOfSquares;

    /**
     * Default constructor.
     * @param numberOfSquares the number of squares to pixelate the image with.
     */
    public PixelImage(int numberOfSquares) {
        this.numberOfSquares = numberOfSquares;
    }

    @Override
    public void run(ImageModelInterface model) throws IllegalStateException {
        model.pixelate(numberOfSquares);

    }
}

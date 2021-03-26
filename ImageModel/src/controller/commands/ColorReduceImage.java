package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;



/**
 * This command runs the reduce color image operation.
 */
public class ColorReduceImage implements ImageCommand {
    private int numberOfColors;

    /**
     * Default constructor.
     * @param numberOfColors the number of colors in the output image.
     */
    public ColorReduceImage(int numberOfColors) {
        this.numberOfColors = numberOfColors;
    }

    @Override
    public void run(ImageModelInterface model) throws IllegalStateException {
        model.reduceColor(numberOfColors);

    }
}

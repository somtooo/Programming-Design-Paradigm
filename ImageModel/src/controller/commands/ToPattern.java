package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

/**
 * This command runs the crossStitch image operation.
 */
public class ToPattern implements ImageCommand {
    @Override
    public void run(ImageModelInterface model) throws IllegalStateException {
        model.crossStitch();

    }
}

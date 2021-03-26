package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

public class ToPattern implements ImageCommand {
    @Override
    public void run(ImageModelInterface model) {
        model.crossStitch();

    }
}

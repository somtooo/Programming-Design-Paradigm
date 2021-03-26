package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

public class SepiaImage implements ImageCommand {
    @Override
    public void run(ImageModelInterface model) {
        model.sepia();

    }
}

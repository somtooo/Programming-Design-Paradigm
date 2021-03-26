package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

public class GreyScaleImage implements ImageCommand {
    @Override
    public void run(ImageModelInterface model) {
        model.greyScale();

    }
}

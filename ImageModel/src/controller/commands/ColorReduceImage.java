package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

public class ColorReduceImage implements ImageCommand {
    private int numberOfColors;
    public ColorReduceImage(int numberOfColors) {
        this.numberOfColors = numberOfColors;
    }

    @Override
    public void run(ImageModelInterface model) {
        model.reduceColor(numberOfColors);

    }
}

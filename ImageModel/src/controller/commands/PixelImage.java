package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

public class PixelImage implements ImageCommand {
    private int numberOfSquares;

    public PixelImage(int numberOfSquares) {
        this.numberOfSquares = numberOfSquares;
    }

    @Override
    public void run(ImageModelInterface model) {
        model.pixelate(numberOfSquares);

    }
}

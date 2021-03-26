package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

public class SharpenImage implements ImageCommand {
    private int intensity;
    public SharpenImage(int intensity) {
        this.intensity = intensity;
    }

    @Override
    public void run(ImageModelInterface model) {
        model.sharpen(intensity);

    }
}

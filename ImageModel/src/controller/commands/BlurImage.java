package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

public class BlurImage implements ImageCommand {
    private final int intensity;
    public BlurImage(int intensity) {
        this.intensity = intensity;
    }

    @Override
    public void run(ImageModelInterface model) {
        model.blur(intensity);
    }
}

package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

import java.io.IOException;

public class LoadImage implements ImageCommand {
    private final String fileName;
    public LoadImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run(ImageModelInterface model) throws IOException {
        model.loadImage(fileName);

    }
}

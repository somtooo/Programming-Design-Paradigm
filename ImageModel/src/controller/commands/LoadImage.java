package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

import java.io.IOException;
import java.util.Objects;

/** This command runs load image operations. */
public class LoadImage implements ImageCommand {
    private final String absolutePath;

    /**
     * Default constructor.
     * @param absolutePath the absolute path of the image to load.
     */
    public LoadImage(String absolutePath) {
        Objects.requireNonNull(absolutePath);
        this.absolutePath = absolutePath;
    }


    @Override
    public void run(ImageModelInterface model) throws IOException, IllegalStateException, IllegalArgumentException {
        model.loadImage(absolutePath);
    }
}

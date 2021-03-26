package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

import java.io.IOException;

public class Save implements ImageCommand {
    private final String filename;

    public Save(String filename) {
        this.filename = filename;
    }

    @Override
    public void run(ImageModelInterface model) throws IOException {
        String[] arrOfStr = filename.split("\\.", 2);
        if (arrOfStr[1].equals("txt")) {
            model.saveTextToFile(filename);
        } else {
            model.saveImage(filename);
        }
    }
}

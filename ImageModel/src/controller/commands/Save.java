package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

import java.io.IOException;


/**
 * This command runs save files operation.
 */
public class Save implements ImageCommand {
    private final String filename;
    /**
     * Default constructor.
     * @param filename the name of the file.
     */
    public Save(String filename) {
        this.filename = filename;
    }

    @Override
    public void run(ImageModelInterface model) throws IllegalStateException, IOException {
        String[] arrOfStr = filename.split("\\.", 2);
        if (arrOfStr[1].equals("txt")) {
            model.saveTextToFile(filename);
        } else {
            model.saveImage(filename);
        }
    }
}

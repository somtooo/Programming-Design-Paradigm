package images;

import controller.Controller;
import controller.IController;
import imagemodel.ImageModel;
import imagemodel.ImageModelInterface;
import java.io.FileReader;
import java.io.IOException;


/** Represents functionality that shows the results of manipulated images. */
public class ProgramRunner {

  /**
   * Manipulates images to show some interesting effects.
   *
   * @param args as input from the user.
   * @throws IOException something goes wrong when controller writes output.
   */
  public static void main(String[] args) throws IOException {
    Readable reader = new FileReader(args[0]);
    IController control = new Controller(reader, System.out);
    ImageModelInterface modelInterface = new ImageModel();
    try {
      control.start(modelInterface);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}


package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

import java.io.IOException;

/** This command runs the crossStitch image operation. */
public class ToPattern implements ImageCommand {

  @Override
  public void run(ImageModelInterface model, int[][][] image)
          throws IllegalStateException, IllegalArgumentException, IOException {
    if (image == null | model == null) {
      throw new IllegalArgumentException("Null arguments not allowed");
    }
    model.crossStitch(image);
  }
}

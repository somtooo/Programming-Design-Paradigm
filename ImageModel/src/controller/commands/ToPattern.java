package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

/** This command runs the crossStitch image operation. */
public class ToPattern implements ImageCommand {

  @Override
  public void run(ImageModelInterface model)
      throws IllegalStateException, IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Null arguments not allowed");
    }
    model.crossStitch();
  }
}

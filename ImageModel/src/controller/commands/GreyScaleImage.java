package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

/** This command runs the greyScale color image operation. */
public class GreyScaleImage implements ImageCommand {

  @Override
  public void run(ImageModelInterface model, int[][][] image)
      throws IllegalStateException, IllegalArgumentException {
    if (image == null | model == null) {
      throw new IllegalArgumentException("Null arguments not allowed");
    }
    model.greyScale(image);
  }
}

package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

/** This command runs the blur image operation. */
public class BlurImage implements ImageCommand {
  private final int intensity;

  /**
   * Default constructor.
   *
   * @param intensity the intensity of the blur.
   */
  public BlurImage(int intensity) {
    this.intensity = intensity;
  }

  @Override
  public void run(ImageModelInterface model, int[][][] image)
      throws IllegalStateException, IllegalArgumentException {
    if (image == null | model == null) {
      throw new IllegalArgumentException("Null arguments not allowed");
    }
    model.blur(intensity, image);
  }
}

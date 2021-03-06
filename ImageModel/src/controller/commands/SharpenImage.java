package controller.commands;

import controller.ImageCommand;
import imagemodel.ImageModelInterface;

/** This command runs the sharpen image operation. */
public class SharpenImage implements ImageCommand {
  private final int intensity;

  /**
   * Default constructor.
   *
   * @param intensity the intensity of the sharpen.
   */
  public SharpenImage(int intensity) {
    this.intensity = intensity;
  }

  @Override
  public void run(ImageModelInterface model)
      throws IllegalStateException, IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Null arguments not allowed");
    }
    model.sharpen(intensity);
  }
}

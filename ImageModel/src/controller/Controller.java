package controller;

import controller.commands.BlurImage;
import controller.commands.ColorReduceImage;
import controller.commands.GreyScaleImage;
import controller.commands.MosaicImage;
import controller.commands.PixelImage;
import controller.commands.Save;
import controller.commands.SepiaImage;
import controller.commands.SharpenImage;
import controller.commands.ToPattern;
import imagemodel.ImageModelInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Implements the IController and represents methods that receives all its inputs from a Readable
 * object and transmits all outputs to an Appendable object.
 */
public class Controller implements IController {
  private final Readable in;
  private final Appendable out;
  private final Map<String, Function<Scanner, ImageCommand>> supportedCommands;

  /**
   * Default Constructor.
   *
   * @param in the data or commands being passed in.
   * @param out the response or output from the controller.
   */
  public Controller(Readable in, Appendable out) {
    Objects.requireNonNull(in, "in cannot be null");
    Objects.requireNonNull(out, "out cannot be null");
    this.in = in;
    this.out = out;
    supportedCommands = new HashMap<>();
    loadCommands();
  }

  @Override
  public void start(ImageModelInterface model) throws IOException, IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("model cant be null");
    }
    Scanner command = new Scanner(this.in);
    int[][][] image = new int[0][][];

    while (command.hasNext()) {
      String input = command.next();
      if (input.equals("load")) {
        try {
          image = model.loadImage(command.next());
          this.out.append(String.format("%s command was carried out successfully\n", input));
        } catch (IOException | NoSuchElementException e) {
          out.append(String.format("%s command was not carried out successfully\n", input));
        }
      }

      Function<Scanner, ImageCommand> imageCommandFunction =
          supportedCommands.getOrDefault(input, null);
      processCommand(model, command, input, imageCommandFunction, image);
    }
  }

  /**
   * Process the command and appends the output as status or error.
   *
   * @param model the model to be used.
   * @param command the command to process.
   * @param input the current input;
   * @param imageCommandFunction the function of the command.
   * @param image the image to process the command on.
   * @throws IOException if something is wrong appending to output.
   */
  private void processCommand(
      ImageModelInterface model,
      Scanner command,
      String input,
      Function<Scanner, ImageCommand> imageCommandFunction,
      int[][][] image)
      throws IOException {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ImageCommand imageCommands;
    if (imageCommandFunction == null) {
      if (!input.equals("load")) {
        this.out.append(String.format("Sorry we do not support the %s command currently\n", input));
      }

    } else {
      findCommandAndRun(model, command, input, imageCommandFunction, image);
    }
  }

  /**
   * Finds the command by looking at the HashMap and runs it.
   *
   * @param model the model to be used.
   * @param command the command from the user.
   * @param input  the extra inputs from the user.
   * @param imageCommandFunction the command function to get.
   * @param image the image to work on.
   * @throws IOException if something is wrong appending to output.
   */
  private void findCommandAndRun(
      ImageModelInterface model,
      Scanner command,
      String input,
      Function<Scanner, ImageCommand> imageCommandFunction,
      int[][][] image)
      throws IOException {
    ImageCommand imageCommands;

    try {
      imageCommands = imageCommandFunction.apply(command);
      imageCommands.run(model, image);
      this.out.append(String.format("%s command was carried out successfully\n", input));
    } catch (IOException e) {
      this.out.append(String.format("%s command was not carried out successfully\n", input));
    } catch (IllegalStateException e) {
      this.out.append(
          String.format(
              "%s command was not carried out successfully make sure image is loaded before"
                  + " calling image operation\n",
              input));
    } catch (IllegalArgumentException | NoSuchElementException e) {
      this.out.append(
          String.format(
              "%s command was not carried out successfully check ReadMe for how to use "
                  + "commands\n",
              input));
    }
  }

  /** Loads the supported commands to a hashMap for easy access. */
  private void loadCommands() {
    supportedCommands.put("blur", input -> new BlurImage(input.nextInt()));
    supportedCommands.put("reduce", input -> new ColorReduceImage(input.nextInt()));
    supportedCommands.put("sharpen", input -> new SharpenImage(input.nextInt()));
    supportedCommands.put("pixelate", input -> new PixelImage(input.nextInt()));
    supportedCommands.put("toMosaic", input -> new MosaicImage(input.nextInt()));
    supportedCommands.put("save", input -> new Save(input.next()));
    supportedCommands.put("greyScale", input -> new GreyScaleImage());
    supportedCommands.put("sepia", input -> new SepiaImage());
    supportedCommands.put("pattern", input -> new ToPattern());
  }
}

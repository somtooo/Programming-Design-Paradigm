package controller;

import controller.commands.*;
import imagemodel.ImageModel;
import imagemodel.ImageModelInterface;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    this.in = in;
    this.out = out;
    supportedCommands = new HashMap<>();
    loadCommands();
  }

  public static void main(String[] args) throws IOException {
    Readable reader = new FileReader(args[0]);
    IController control = new Controller(reader, System.out);
    ImageModelInterface modelInterface = new ImageModel();
    control.start(modelInterface);
    //

  }

  @Override
  public void start(ImageModelInterface model) throws IOException {
    Scanner command = new Scanner(this.in);

    while (command.hasNext()) {
      ImageCommand imageCommands;
      String input = command.next();
      model = checkLoadCommand(model, command, input);

      Function<Scanner, ImageCommand> imageCommandFunction =
          supportedCommands.getOrDefault(input, null);
      processCommand(model, command, input, imageCommandFunction);
    }
  }

  /**
   * Process the command and appends the output as status or error.
   * @param model the model to be used.
   * @param command the command to process.
   * @param input the current input;
   * @param imageCommandFunction the function of the command.
   * @throws IOException if files couldn't be found.
   * @throws IllegalStateException if the image in the model is empty.
   */
  private void processCommand(
      ImageModelInterface model,
      Scanner command,
      String input,
      Function<Scanner, ImageCommand> imageCommandFunction)
      throws IOException {
    ImageCommand imageCommands;
    if (imageCommandFunction == null) {
      if (!input.equals("load")) {
        this.out.append(String.format("Sorry we do not support the %s command currently\n", input));
      }

    } else {
      try {
        imageCommands = imageCommandFunction.apply(command);
        imageCommands.run(model);
        this.out.append(String.format("%s command was carried out successfully\n", input));
      } catch (IOException e) {
        this.out.append("Sorry couldn't find the file specified\n");
      } catch (IllegalStateException e) {
        this.out.append("Please load an image first before applying image operations");
      }
    }
  }

  /**
   * Checks to see if the load command is passed in. If so it replaces the model with a new model
   * that contains the image.
   *
   * @param model the model to replace or return as is.
   * @param command the command to check.
   * @param input the extra inputs to the command.
   * @return a model.
   * @throws IOException if the file was not found.
   */
  private ImageModelInterface checkLoadCommand(
      ImageModelInterface model, Scanner command, String input) throws IOException {
    if (input.equals("load")) {
      try {
        model = new ImageModel(model.loadImage(command.next()));
      } catch (IOException e) {
        out.append("Sorry couldn't find the file\n");
      }
    }
    return model;
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

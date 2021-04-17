package controller;

import controller.commands.*;
import imagemodel.ImageModelInterface;
import view.BashViewInterface;

import java.io.IOException;
import java.io.StringReader;
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
  private Readable in;
  private final Appendable out;
  private final Map<String, Function<Scanner, ImageCommand>> supportedCommands;
  private  BashViewInterface bashView;
  private ImageModelInterface model;

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

  public Controller(ImageModelInterface model, BashViewInterface view) {
    this(new StringReader(""), new StringBuffer());
    this.model = model;
    this.bashView = view;
  }

  @Override
  public void setView() {
    bashView.setFeatures(this);
  }

  @Override
  public void start(ImageModelInterface model) throws IOException, IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("model cant be null");
    }
    this.model = model;
    Scanner command = new Scanner(this.in);
    while (command.hasNext()) {
      String input = command.next();
      Function<Scanner, ImageCommand> imageCommandFunction =
          supportedCommands.getOrDefault(input, null);
      processCommand(model, command, input, imageCommandFunction);
    }
  }

  @Override
  public void processInput(String text) {
    bashView.clearInputString();
    bashView.setAddToList(text);
  }

  @Override
  public void runCommand(Object[] toArray) {
    in = new StringReader(getString(toArray));
    try {
      bashView.clearListScreen();
      start(model);
      bashView.throwSuccess(out.toString(),"Batch Load Success");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public  void updateScript(String text) {
    bashView.updateList(text, bashView.getIndex());
  }

  @Override
  public void processMouseEvent() {
    bashView.setInputString(bashView.getInputValue());
  }

  private String getString(Object[] toArray) {
    StringBuilder builder = new StringBuilder();
    for (Object element : toArray ) {
      builder.append(element).append("\n");
    }

    return builder.toString();
  }

  /**
   * Process the command and appends the output as status or error.
   *
   * @param model the model to be used.
   * @param command the command to process.
   * @param input the current input;
   * @param imageCommandFunction the function of the command.
   * @throws IOException if something is wrong appending to output.
   */
  private void processCommand(
      ImageModelInterface model,
      Scanner command,
      String input,
      Function<Scanner, ImageCommand> imageCommandFunction)
      throws IOException {
    if (imageCommandFunction == null) {
        this.out.append(String.format("Sorry we do not support the %s command currently\n", input));
    } else {
      findCommandAndRun(model, command, input, imageCommandFunction);
    }
  }

  /**
   * Finds the command by looking at the HashMap and runs it.
   *
   * @param model the model to be used.
   * @param command the command from the user.
   * @param input  the extra inputs from the user.
   * @param imageCommandFunction the command function to get.
   * @throws IOException if something is wrong appending to output.
   */
  private void findCommandAndRun(
      ImageModelInterface model,
      Scanner command,
      String input,
      Function<Scanner, ImageCommand> imageCommandFunction)
      throws IOException {
    ImageCommand imageCommands;

    try {
      imageCommands = imageCommandFunction.apply(command);
      imageCommands.run(model);
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
    supportedCommands.put("load", input -> new LoadImage(input.next()));
  }
}

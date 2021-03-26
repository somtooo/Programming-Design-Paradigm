package controller;

import controller.commands.*;
import imagemodel.ImageModel;
import imagemodel.ImageModelInterface;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class Controller {
    private final Readable in;
    private final Appendable out;
    private Map<String, Function<Scanner,ImageCommand >> supportedCommands;

    public Controller(Readable in, Appendable out) {
        this.in = in;
        this.out = out;
        supportedCommands = new HashMap<>();
        loadCommands();
    }

    public void start(ImageModelInterface model) throws IOException {
        Scanner command = new Scanner(this.in);
        while (command.hasNext()) {
            ImageCommand imageCommands;
            String input = command.next();
            switch (input) {
                case "greyScale":
                    model.greyScale();
                    break;
                case "toSepia":
                    model.sepia();
                    break;
                case "pattern":
                    model.crossStitch();
                    break;
            }

            Function<Scanner, ImageCommand> imageCommandFunction = supportedCommands.getOrDefault(input, null);
            if (imageCommandFunction == null) {
                this.out.append("Sorry we do not currently support that command"+"\\n");
            } else {
                try{
                    imageCommands = imageCommandFunction.apply(command);
                    imageCommands.run(model);
                    this.out.append(String.format("%s command was carried out successfully\n",input));
                } catch (IOException e) {
                    this.out.append("Sorry couldn't find the file specified\n");
                }

            }


        }
    }

    private void loadCommands() {
        supportedCommands.put("blur",input -> new BlurImage(input.nextInt()));
        supportedCommands.put("reduce",input -> new ColorReduceImage(input.nextInt()));
        supportedCommands.put("sharpen",input -> new SharpenImage(input.nextInt()));
        supportedCommands.put("pixelate",input -> new PixelImage(input.nextInt()));
        supportedCommands.put("toMosaic",input -> new MosiacImage(input.nextInt()));
        supportedCommands.put("save",input -> new Save(input.next()));
        supportedCommands.put("load",input -> new LoadImage(input.next()));



    }

    public static void main(String[] args) throws IOException {
        Readable reader = new FileReader(args[0]);
        Controller control = new Controller(reader,System.out);
        ImageModelInterface modelInterface = new ImageModel();
        control.start(modelInterface);
    //

  }
}

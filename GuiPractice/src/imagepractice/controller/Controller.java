package imagepractice.controller;

import imagepractice.PracticeViewInterface;
import imagepractice.UnifiedViewInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller implements  TotalFeatures {
    private PracticeViewInterface view;

    public Controller() {


    }

    public void setView(PracticeViewInterface viewToSet) {
        view = viewToSet;
        view.setFeatures(this);

    }

    @Override
    public void loadImage() {
    String filename = view.getImage();
    System.out.println(filename);
    try {
      BufferedImage image = ImageIO.read(new File(filename));
      view.setImagePanel(image);
      // Add this to the GUI
    } catch (IOException ex) {
      System.out.println(ex);
    }
    }

    @Override
    public void blurImage() {

        try {
      BufferedImage image =
          ImageIO.read(
              new File(
                  "C:\\Users\\chukw\\IdeaProjects\\GuiPractice\\src\\imagepractice\\images\\SnowyTree.png"));
            System.out.println("I blurred image");
            view.setImagePanel(image);
            view.setSliderListenerToBlur(this);

            // Add this to the GUI
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void sharpenImage() {
        try {
            BufferedImage image =
                    ImageIO.read(
                            new File(
                                    "C:\\Users\\chukw\\IdeaProjects\\GuiPractice\\src\\imagepractice\\images\\SnowyTree.png"));
            System.out.println("I Sharpened image");
            view.setImagePanel(image);
            // Add this to the GUI
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}

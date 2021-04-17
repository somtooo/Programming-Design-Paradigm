package view;

import controller.Controller;
import controller.IController;
import imagemodel.ImageModel;
import imagemodel.ImageModelInterface;

public class BatchScriptRunner implements BashInterface{

    @Override
    public void start() {
       main(new String[0]);
    }

  public static void main(String[] args) {
      BashViewInterface view = new BashView();
      ImageModelInterface model = new ImageModel();
      IController controller = new Controller(model, view);
      controller.setView();
      view.start(view);
  }
}

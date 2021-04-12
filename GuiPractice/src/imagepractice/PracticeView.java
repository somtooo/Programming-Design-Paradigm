package imagepractice;

import javax.swing.*;
import java.awt.*;

public class PracticeView extends JFrame{
    public PracticeView() {
        super("Practice View");
        this.setSize(1500,900);
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        Scrollable scrollable = new Scrollable();
        this.add(scrollable,BorderLayout.CENTER);
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0,20,0);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        bottom.add(slider);
        this.add(bottom, BorderLayout.SOUTH);
        JMenuBar menuBar = new MenuBar(scrollable, slider);
        this.setJMenuBar(menuBar);



    }

    public void start() {
        this.setVisible(true);
    }

  public static void main(String[] args) {
      PracticeView frame = new PracticeView();
      frame.start();

      }

}


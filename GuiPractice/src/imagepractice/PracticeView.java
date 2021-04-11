package imagepractice;

import imagedemo.Scrollable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PracticeView extends JFrame{
    public PracticeView() {
        super("Practice View");
        this.setSize(1500,900);
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        Scrollable scrollable = new Scrollable();
        this.setContentPane(scrollable);
        JMenuBar menuBar = new MenuBar(scrollable);
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


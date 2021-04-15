package imagepractice;

import javax.swing.*;

public class darkui {
    private JPanel Jpanel1;
    private JPanel root;

  public static void main(String[] args) {
      JFrame frame = new JFrame("App");
      frame.setContentPane(new darkui().root);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
  }
}

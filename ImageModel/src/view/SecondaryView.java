package view;

import controller.IController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * Represents a secondary view that is used to serve other functionality the main view cant It's
 * also the parent to the ColorPicker View and Implements the SecondaryView Interface.
 */
public class SecondaryView extends JFrame implements SecondaryViewInterface {

  protected final JTextField input;
  protected final JList<String> list;
  protected final JButton addToList;
  protected final JButton run;
  protected final DefaultListModel<String> defaultModel = new DefaultListModel<String>();
  protected final JButton update;
  protected final JLabel info;
  protected final JPanel right;

  /** Default Constructor, builds the view before start method is called. */
  public SecondaryView() {
    JPanel root = new JPanel();
    root.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    centreWindow(this, 800, 600);
    this.setSize(800, 600);

    JPanel top = new JPanel();
    info = new JLabel();
    Color header = new Color(21, 25, 28);
    Color background = new Color(34, 40, 44);
    Color thingsInFront = new Color(44, 52, 58);
    root.setBackground(thingsInFront);
    top.setBackground(header);
    info.setFont(new Font("Roboto Th", Font.PLAIN, 36));
    info.setText("Write/Run Script Commands");
    info.setForeground(new Color(240, 240, 240));
    top.add(info);

    JPanel left = new JPanel();
    left.setBackground(thingsInFront);
    list = new JList<String>(defaultModel);
    list.setFont(new Font("Roboto Th", Font.PLAIN, 28));
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    right = new JPanel();
    right.setBackground(thingsInFront);
    FlowLayout flow = new FlowLayout();

    flow.setVgap(20);
    right.setLayout(flow);
    right.setPreferredSize(new Dimension(486, 510));
    input = new JTextField();
    input.setText("");
    input.setPreferredSize(new Dimension(361, 52));
    input.setFont(new Font("Roboto Th", Font.PLAIN, 28));
    addToList = new JButton("Send to Script");
    addToList.setPreferredSize(new Dimension(361, 52));
    addToList.setFont(new Font("Roboto Th", Font.PLAIN, 28));

    run = new JButton("Run Script");
    run.setFont(new Font("Roboto Th", Font.PLAIN, 28));
    run.setPreferredSize(new Dimension(361, 52));

    update = new JButton("Update Script");
    update.setFont(new Font("Roboto Th", Font.PLAIN, 28));
    update.setPreferredSize(new Dimension(361, 52));

    right.add(input);
    right.add(addToList);
    right.add(run);
    right.add(update);
    JScrollPane pane = new JScrollPane(list);
    root.add(top, BorderLayout.NORTH);
    root.add(pane, BorderLayout.WEST);
    root.add(right, BorderLayout.EAST);
    add(root);
  }

  public static void centreWindow(JFrame frame, int width, int height) {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);

    // calculate perfect center
    int perf_x = x - width / 2;
    int perf_y = y - height / 2;

    frame.setLocation(perf_x, perf_y);
  }

  @Override
  public void start() {
    this.setVisible(true);
  }

  @Override
  public void setFeatures(IController controller) {
    addToList.addActionListener(e -> controller.processInput(input.getText()));
    run.addActionListener(e -> controller.runCommand(defaultModel.toArray()));

    list.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent event) {
            input.setText(list.getSelectedValue());
          }
        });

    update.addActionListener(e -> controller.updateScript(input.getText()));
    input.addKeyListener(
        new KeyListener() {
          @Override
          public void keyTyped(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              controller.processInput(input.getText());
            }
          }

          @Override
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              controller.processInput(input.getText());
            }
          }

          @Override
          public void keyReleased(KeyEvent e) {}
        });
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }

  @Override
  public void clearListScreen() {
    defaultModel.clear();
  }

  @Override
  public void setAddToList(String command) {
    defaultModel.addElement(command);
  }

  @Override
  public void throwSuccess(String success, String successType) {
    JOptionPane.showMessageDialog(this, success, successType, JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void updateList(String command, int index) {
    defaultModel.setElementAt(command, index);
    clearInputString();
  }

  @Override
  public int getIndex() {
    return list.getSelectedIndex();
  }

  @Override
  public String getInputValue() {
    return list.getSelectedValue();
  }

  @Override
  public void setInputString(String text) {
    input.setText(text);
  }

  @Override
  public void setListColor(int r, int g, int b) {
    list.setBackground(new Color(r, g, b));
  }

  @Override
  public int[] getListElementColor() {
    int[] rgb = new int[3];
    rgb[0] = list.getForeground().getRed();
    rgb[1] = list.getForeground().getGreen();
    rgb[2] = list.getForeground().getBlue();
    return rgb;
  }

  @Override
  public void setListElementColor(Color color) {
    list.setForeground(color);
  }
}

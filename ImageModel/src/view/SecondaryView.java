package view;

import controller.IController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
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
import view.utilities.ViewUtilities;

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
    ViewUtilities.centreWindow(this, 800, 600);
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

  @Override
  public void start() {
    this.setVisible(true);
  }

  @Override
  public void setFeatures(IController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("Null not allowed");
    }
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
            Objects.requireNonNull(e, "null not allowed");
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              controller.processInput(input.getText());
            }
          }

          @Override
          public void keyPressed(KeyEvent e) {
            Objects.requireNonNull(e, "null not allowed");
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
    Objects.requireNonNull(command, "Input can't be null");
    defaultModel.addElement(command);
  }

  @Override
  public void throwSuccess(String success, String successType) {
    Objects.requireNonNull(success, "Input cant be null");
    Objects.requireNonNull(successType, "Input can't be null");
    JOptionPane.showMessageDialog(this, success, successType, JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void updateList(String command, int index) {
    Objects.requireNonNull(command, "Input cant be null");
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
    Objects.requireNonNull(text, "Input cant be null");
    input.setText(text);
  }
}

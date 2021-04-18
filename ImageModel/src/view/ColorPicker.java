package view;

import controller.IController;
import controller.TotalFeatures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;


/**
 * Child class to SecondaryView represents a view that handles all color functionality of the GUI.
 */
public class ColorPicker extends SecondaryView implements ColorPickerInterface {

  private final JButton submitMultipleSelect;
  private final JMenuItem exit;
  private final JMenuItem switchMultiple;
  private final JMenuItem switchBack;

  /** Default constructor builds the view before start is called. */
  public ColorPicker() {
    super();
    JMenuBar menu = new JMenuBar();
    menu.setBackground(new Color(21, 25, 28));
    JMenu settings = new JMenu("Settings");
    settings.setForeground(Color.white);
    menu.add(settings);
    exit = new JMenuItem("Exit");
    switchMultiple = new JMenuItem("Switch To Select Multiple Colors");
    switchBack = new JMenuItem("Switch To Select Single Colors");
    settings.add(switchMultiple);
    settings.add(switchBack);
    settings.add(exit);
    this.setJMenuBar(menu);

    info.setText("Select DMC Color to Replace With");
    input.setEditable(false);
    addToList.setText("Replace with this");
    run.setText("Load Dmc Palette");
    update.setText("Remove Color clicked");

    submitMultipleSelect = new JButton("Submit Multiple DMC");
    submitMultipleSelect.setFont(new Font("Roboto Th", Font.PLAIN, 28));
    submitMultipleSelect.setPreferredSize(new Dimension(361, 52));
    submitMultipleSelect.setVisible(false);

    right.add(submitMultipleSelect);
  }

    @Override
    public void resetFeatures(TotalFeatures controller) {
        addToList.addActionListener(e -> controller.replaceColor(list.getSelectedValue()));
        run.addActionListener(e -> controller.loadDmc());
        list.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent event) {
                        input.setText(list.getSelectedValue());
                        controller.handleColorClick(list.getSelectedValue());
                    }
                });

        update.addActionListener(e -> controller.removeColorFromImage(list.getSelectedValue()));

        switchMultiple.addActionListener(
                e -> {
                    addToList.setVisible(false);
                    input.setVisible(false);
                    list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    submitMultipleSelect.setVisible(true);
                });

        exit.addActionListener(e -> System.exit(0));

        switchBack.addActionListener(
                e -> {
                    addToList.setVisible(true);
                    input.setVisible(true);
                    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    submitMultipleSelect.setVisible(false);
                });

        submitMultipleSelect.addActionListener(
                e -> controller.handleMultipleSelection(list.getSelectedValuesList()));
    }

  @Override
  public void start() {
    super.start();
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

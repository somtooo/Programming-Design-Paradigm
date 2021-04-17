package view;

import controller.Controller;
import controller.IController;
import imagemodel.ImageModel;
import imagemodel.ImageModelInterface;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BashView extends JPanel implements BashViewInterface {

    private final JTextField input;
    private final JList<String> list;
    private final JButton addToList;
    private final JButton run;
    private final DefaultListModel<String> dm= new DefaultListModel<String>();
    private final JButton update;


    public BashView()  {
        BorderLayout layout = new BorderLayout();
        layout.setVgap(20);

        setLayout(layout);
        setPreferredSize(new Dimension(800,600));
        JPanel top = new JPanel();
        JLabel info = new JLabel();
        Color header = new Color(21,25,28);
        Color background = new Color(34,40,44);
        Color thingsInFront = new Color(44,52,58);
        setBackground(thingsInFront);
        top.setBackground(header);
        info.setFont(new Font("Roboto Th",Font.PLAIN, 36));
        info.setText("Write/Run Script Commands");
        info.setForeground(new Color(240,240,240));
        top.add(info);
        JPanel left = new JPanel();
        left.setBackground(thingsInFront);
        list = new JList<String>(dm);
        list.setFont(new Font("Roboto Th",Font.PLAIN, 30));
        JScrollPane pane = new JScrollPane(list);
        JPanel right = new JPanel();
        right.setBackground(thingsInFront);
        FlowLayout flow = new FlowLayout();
        flow.setVgap(20);
        right.setLayout(flow);

        right.setPreferredSize(new Dimension(486,510));
        input = new JTextField();
        input.setText("");
        input.setPreferredSize(new Dimension(361, 52));
        input.setFont(new Font("Roboto Th",Font.PLAIN,28));
        addToList = new JButton("Send to Script");
        addToList.setPreferredSize(new Dimension(361,52));
        addToList.setFont(new Font("Roboto Th",Font.PLAIN,28));
        run = new JButton("Run Script");
        run.setFont(new Font("Roboto Th",Font.PLAIN,28));
        run.setPreferredSize(new Dimension(361,52));

        update = new JButton("Update Script");
        update.setFont(new Font("Roboto Th",Font.PLAIN,28));
        update.setPreferredSize(new Dimension(361,52));

        right.add(input);
        right.add(addToList);
        right.add(run);
        right.add(update);
        this.add(top, BorderLayout.NORTH);
        this.add(pane, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
    }


    @Override
    public void start(BashViewInterface view) {
        JFrame frame = new JFrame("Bash");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        BashView newContentPane = (BashView) view;
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void setFeatures(IController controller) {
        addToList.addActionListener(e ->controller.processInput(input.getText()));
        run.addActionListener(e -> controller.runCommand(dm.toArray()));
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                input.setText(list.getSelectedValue());
            }
        });
        update.addActionListener(e -> controller.updateScript(input.getText()));
        input.addKeyListener(new KeyListener() {
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
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    @Override
    public void clearInputString() {
        input.setText("");
    }

    @Override
    public  void clearListScreen() {
        dm.clear();
    }

    @Override
    public void setAddToList(String command) {
        dm.addElement(command);
    }

    @Override
    public  void throwSuccess(String success, String successType) {
        JOptionPane.showMessageDialog(this, success, successType,JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void updateList(String command, int index) {
        dm.setElementAt(command,index);
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


}



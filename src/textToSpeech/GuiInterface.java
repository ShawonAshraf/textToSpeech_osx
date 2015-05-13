package textToSpeech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shawon on 5/13/15.
 */
public class GuiInterface {

    private JFrame mainWindow;
    private GridLayout gl; // the main layout for the window

    private JLabel commanLabel;
    private JButton sayButton;
    private JButton clearButton;

    private JTextField textField;


    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem exit;


    private JPanel upperPanel; // will use border layout
    private JPanel lowerPanel; // will use flow layout


    private final static String defaultText = "Write your message";

    public GuiInterface() {

        initGUI();
        addCompsToUpperPanel();
        addCompsToLowerPanel();
        addToMenuBar();
        addToMainWindow();
        setWindowProperties();
        eventHandlers();

    }

    private void initGUI() {

        mainWindow = new JFrame("Text To Speech for OS X");
        gl = new GridLayout(2,1,2,2);

        commanLabel = new JLabel("Enter what you want to hear!");
        sayButton = new JButton("Say it!");
        clearButton = new JButton("Clear it!");

        textField = new JTextField();
        textField.setText(defaultText);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        exit = new JMenuItem("Exit");

        upperPanel = new JPanel();
        lowerPanel = new JPanel();

    }

    private void addToMainWindow() {

        mainWindow.setJMenuBar(menuBar);

        mainWindow.setLayout(gl);

        mainWindow.add(upperPanel);
        mainWindow.add(lowerPanel);

    }

    private void setWindowProperties() {

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(375, 220);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);

    }


    private void addToMenuBar() {

        fileMenu.add(exit);
        menuBar.add(fileMenu);

    }


    private void addCompsToUpperPanel() {

        upperPanel.setLayout(new BorderLayout());

        upperPanel.add(commanLabel, BorderLayout.NORTH);
        upperPanel.add(textField, BorderLayout.SOUTH);

    }

    private void addCompsToLowerPanel() {

        lowerPanel.setLayout(new FlowLayout());

        lowerPanel.add(sayButton);
        lowerPanel.add(clearButton);


    }

    private void eventHandlers() {

        Handler handler = new Handler();

        sayButton.addActionListener(handler);
        clearButton.addActionListener(handler);

        exit.addActionListener(handler);

    }

    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //TextToSpeech ts = new TextToSpeech();

            if(e.getSource() == sayButton) {

               try {

                   String command = textField.getText();

                   if(command == "") JOptionPane.showMessageDialog(null, "You didn't write anything");
                   else {

                       Runtime runtime = Runtime.getRuntime();
                       Process p = runtime.exec("say " + command);
                       p.waitFor();

                   }

               }

               catch (Exception x) {

                   JOptionPane.showMessageDialog(null, "Error processing what you wrote in");

               }



            }

            else if(e.getSource() == clearButton) {

                textField.setText(defaultText);

            }

            else if(e.getSource() == exit) {

                System.exit(0);

            }

        }

    }

}

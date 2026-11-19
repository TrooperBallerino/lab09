package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class is a simple application that save a text written by the user in a file.
 * 
 * <p>
 * The user can change the file where they write in with a JFileChooser
 * (the standard file is choosen in {@see Controller})
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();
    private final Controller controller = new Controller();

    /**
     * Creates a new SimpleGUIWithFileChooser.
     */
    public SimpleGUIWithFileChooser() {
        // Setup the canvas
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextComponent inputText = new JTextArea();
        canvas.add(inputText, BorderLayout.CENTER);
        final JButton saveButton = new JButton("Save");
        canvas.add(saveButton, BorderLayout.SOUTH);
        // Setup the inner canvas
        final JPanel innerCanvas = new JPanel();
        innerCanvas.setLayout(new BoxLayout(innerCanvas, BoxLayout.X_AXIS));
        canvas.add(innerCanvas, BorderLayout.NORTH);
        final JTextComponent browseTextField = new JTextField(Controller.STD_FILE);
        browseTextField.setEditable(false);
        innerCanvas.add(browseTextField);
        final JButton browseButton = new JButton("Browse...");
        innerCanvas.add(browseButton);
        // Setup frame
        final JFileChooser fileChooser = new JFileChooser();
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Button Action Listeners
        browseButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ignored) {
                    final var returnVal = fileChooser.showSaveDialog(frame);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        final var selectedFile = fileChooser.getSelectedFile();
                        controller.setCurrentFile(selectedFile);
                        browseTextField.setText(selectedFile.getAbsolutePath());
                    } else if (returnVal != JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(frame, "An error occured");
                    }
                }
            }
        );

        saveButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ignored) {
                    try {
                        controller.writeFile(inputText.getText());
                    } catch (final IOException e) {
                        JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        );
    }

    /**
     * Set the size and display of the SimpleGUI windows.
     */
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     */
    public static void main() {
        new SimpleGUIWithFileChooser().display();
    }
}

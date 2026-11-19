package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileWriter;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    /**
     * Default file where the controller writes.
     */
    public static final String STD_FILE = System.getProperty("user.home")
        + File.separator
        + "output.txt";
    private File currentFile;

    /**
     * Creates a new Controller.
     * By default, the file is output.txt inside the user home directory
     */
    public Controller() {
        this.currentFile = new File(STD_FILE);
    }

    /**
     * Set a new current file.
     * 
     * @param newFile the File that will be set as current file
     */
    public void setCurrentFile(final File newFile) {
        this.currentFile = newFile;
    }

    /**
     * Returns the current file.
     * 
     * @return the current file
     */
    public File getCurrentFile() {
        return currentFile;
    }

    /**
     * Returns the (absolute) path of the current file.
     *
     * @return the path of the current file
     */
    public String getPath() {
       return this.currentFile.getAbsolutePath(); 
    }

    /**
     * Write a string inside the current file.
     * 
     * @param input the string that will be written inside the file
     * @throws IOException if occurs some error during the writing
     */
    public void writeFile(final String input) throws IOException {
        try (var fileWriter = new FileWriter(currentFile, StandardCharsets.UTF_8)) {
            fileWriter.write(input);
        }
    }
}

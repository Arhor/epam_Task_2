/*
 * class: OutputFileWriter
 */

package by.epam.training.service.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * Class OutputFileWriter provides easy interface for writing text to file
 * 
 * @version 1.0 02 Aug 2018
 * @author  Maxim Burishinets
 */
public class OutputFileWriter {

    /**
     * Writes given String to the file
     * 
     * @param path a String that represents path to the file to write to
     * @param source a String of text that will be written to the file
     */
    public void writeText(String path, String source) throws IOException {
        File file = new File(path);
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            br.write(source);
        } catch (IOException e) {
            throw e;
        }
    }
}

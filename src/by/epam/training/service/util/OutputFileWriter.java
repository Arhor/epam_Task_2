/*
 * class: OutputFileWriter
 * 
 * version 1.0 02 Aug 2018
 * 
 * author: Maxim Burishinets
 */

package by.epam.training.service.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFileWriter {

	public void writeText(String path, String source) throws IOException {
		File file = new File(path);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(source);
        } catch (IOException e) {
            throw e;
        }
	}
}
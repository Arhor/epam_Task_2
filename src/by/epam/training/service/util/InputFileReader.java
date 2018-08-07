/*
 * class: InputFileReader
 */

package by.epam.training.service.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class InputFileReader provides easy interface for reading whole text from
 * file
 * 
 * @version 1.0 02 Aug 2018
 * @Author  Maxim Burishinets
 */
public class InputFileReader {

	/**
	 * Reads the file by given path and returns it's content as a String
	 * 
	 * @param path a String that represents path to the file to read from
	 * @return String that contains all content of the file
	 */
	public String readText(String path) throws IOException {
		StringBuilder sb = new StringBuilder();
        File file = new File(path);
        try (FileReader fileReader = new FileReader(file)) {
            int b;
            do {
                b = fileReader.read();
                if (b != -1) {
                    sb.append((char)b);
                }
            } while (b != -1);
        } catch (IOException e) {
            throw e;
        }
        return sb.toString();
	}
}

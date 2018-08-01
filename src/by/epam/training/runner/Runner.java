package by.epam.training.runner;

import by.epam.training.model.*;

import by.epam.training.service.TextParser;
import org.apache.log4j.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Runner {

    private static final Logger LOG = Logger.getLogger(Runner.class);

    public static void main(String[] args) {

        // TODO: find longest anagram represented in the text

        StringBuilder sb = new StringBuilder();
        File file = new File("text.txt");
        try (FileReader fileReader = new FileReader(file)) {
            int b;
            do {
                b = fileReader.read();
                if (b != -1) {
                    sb.append((char)b);
                }
            } while (b != -1);
        } catch (IOException e) {
            LOG.error("I/O exception", e);
        }

        IComposite wholeText = TextParser.parseToParagraphs(sb.toString());

        LOG.info(wholeText.print());

        LOG.info("\n\n" + TextParser.getTextInfo(wholeText));

        File file1 = new File("output.txt");
        try (FileWriter fw = new FileWriter(file1)) {
            fw.write(wholeText.print());
        } catch(IOException e) {
            LOG.error("I/O exception: ", e);
        }
    }
}

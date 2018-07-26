package by.epam.training.runner;

import by.epam.training.model.*;

import by.epam.training.service.TextParser;
import org.apache.log4j.*;

import java.io.File;
import java.io.FileReader;
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

        LOG.info("elements in the text: " + ((CompositeObject)wholeText).size());
        for (int i = 0; i < ((CompositeObject)wholeText).size(); i++) {
            LOG.info(String.format("%2d: %s", i + 1, wholeText.get(i)));
        }
    }
}

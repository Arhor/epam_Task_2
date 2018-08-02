package by.epam.training.runner;

import by.epam.training.model.*;

import by.epam.training.service.TextParser;
import by.epam.training.service.TextRestorer;
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
        File file = new File("input.txt");
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

        IComposite wholeText = null;
        File file1 = new File("parsed_text.txt");
        try (FileWriter fw = new FileWriter(file1)) {
            wholeText = TextParser.parseToParagraph(sb.toString(), fw);
        } catch(IOException e) {
            LOG.error("I/O exception: ", e);
        }

        //LOG.info(wholeText.print());

        //LOG.info("\n\n" + TextParser.getTextInfo(wholeText));

        StringBuilder parsed = new StringBuilder();
        try (FileReader fileReader = new FileReader(file1)) {
            int b;
            do {
                b = fileReader.read();
                if (b != -1) {
                    parsed.append((char)b);
                }
            } while (b != -1);
        } catch (IOException e) {
            LOG.error("I/O exception", e);
        }

        File file2 = new File("output.txt");
        try (FileWriter fileWriter = new FileWriter(file2)) {
            TextRestorer.restore(parsed.toString(), fileWriter);
        } catch (IOException e) {
            LOG.error("I/O exception", e);
        }

        // equality test
        StringBuilder restored = new StringBuilder();
        try (FileReader fileReader = new FileReader(file2)) {
            int b;
            do {
                b = fileReader.read();
                if (b != -1) {
                    restored.append((char)b);
                }
            } while (b != -1);
        } catch (IOException e) {
            LOG.error("I/O exception", e);
        }
        LOG.info("Original text equals restored text: " + sb.toString().equals(restored.toString()));
    }
}

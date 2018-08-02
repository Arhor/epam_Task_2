package by.epam.training.runner;

import by.epam.training.model.*;

import by.epam.training.service.TextParser;
import by.epam.training.service.TextRestorer;
import by.epam.training.service.util.InputFileReader;
import by.epam.training.service.util.OutputFileWriter;

import org.apache.log4j.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Runner {

    private static final Logger LOG = Logger.getLogger(Runner.class);

    public static void main(String[] args) throws IOException {

        // TODO: find longest palindrome represented in the text

    	InputFileReader ifr = new InputFileReader();
    	OutputFileWriter ofw = new OutputFileWriter();
    	
    	String text = ifr.readText("input.txt");
    	
        IComposite wholeText = null;
        File file = new File("parsed_text.txt");
        try (FileWriter fw = new FileWriter(file)) {
            wholeText = TextParser.parseToParagraph(text, fw);
        } catch(IOException e) {
            LOG.error("I/O exception: ", e);
        }

        //LOG.info(wholeText.print());
        //LOG.info("\n\n" + TextParser.getTextInfo(wholeText));
        
        TextRestorer textRestorer = new TextRestorer();
        
        String parsed = ifr.readText("parsed_text.txt");
        String restored = textRestorer.restore(parsed);
        
        ofw.writeText("output.txt", restored);
        
        String result = ifr.readText("output.txt");
        LOG.info("Original text equals restored text: " + text.equals(result));
    }
}

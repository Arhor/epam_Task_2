package by.epam.training.runner;

import by.epam.training.model.*;
import by.epam.training.service.extract.TextExtractor;
import by.epam.training.service.parse.*;
import by.epam.training.service.restore.TextRestorer;
import by.epam.training.service.util.InputFileReader;
import by.epam.training.service.util.OutputFileWriter;

import org.apache.log4j.*;

import java.io.IOException;

public class Runner {

    private static final Logger LOG = Logger.getLogger(Runner.class);

    public static void main(String[] args) throws IOException {

        // TODO: find longest palindrome represented in the text

    	InputFileReader ifr = new InputFileReader();
    	OutputFileWriter ofw = new OutputFileWriter();
    	String text = ifr.readText("input.txt");
    	
    	Parser textParser = new TextParser();
    	Parser paragraphParser = new ParagraphParser();
    	Parser sentenceParser = new SentenceParser();
    	Parser wordParser = new WordParser();
    	
    	textParser.setSuccessor(paragraphParser);
    	paragraphParser.setSuccessor(sentenceParser);
    	sentenceParser.setSuccessor(wordParser);
    	
        IComposite wholeText = textParser.parse(text);
        LOG.info(wholeText.print());
        
        TextExtractor extractor = new TextExtractor();
        
        ofw.writeText("parsed_text.txt", extractor.ExtractText(wholeText));
        TextRestorer textRestorer = new TextRestorer();
        String restored = textRestorer.restore(ifr.readText("parsed_text.txt"));
        ofw.writeText("output.txt", restored);
        String result = ifr.readText("output.txt");
        LOG.info("Original text equals restored text: " + text.equals(result));
    }
}

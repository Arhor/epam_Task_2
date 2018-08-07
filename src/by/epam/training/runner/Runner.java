package by.epam.training.runner;

import by.epam.training.model.*;
import by.epam.training.service.extract.TextExtractor;
import by.epam.training.service.palindrome.Finder;
import by.epam.training.service.parse.*;
import by.epam.training.service.restore.TextRestorer;
import by.epam.training.service.util.InputFileReader;
import by.epam.training.service.util.OutputFileWriter;

import org.apache.log4j.*;

import java.io.IOException;

public class Runner {

    private static final Logger LOG = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        InputFileReader ifr = new InputFileReader();
        OutputFileWriter ofw = new OutputFileWriter();
        
        String text = "";
        try {
        	process("Reading input.txt file");
        	text = ifr.readText("input.txt");
        	LOG.info("success\n");
        } catch (IOException e) {
        	LOG.info("I/O exception is occured");
        	LOG.error("I/O exception", e);
        	return;
        } catch (InterruptedException e) {
        	LOG.info("Exception in the main thread is occured");
        	LOG.error("Interrupted exception", e);
        	return;
        }
        
        if (text.length() > 0) {
        	try {
        		Parser textParser = new TextParser();
                Parser paragraphParser = new ParagraphParser();
                Parser sentenceParser = new SentenceParser();
                Parser wordParser = new WordParser();
                
                textParser.setSuccessor(paragraphParser);
                paragraphParser.setSuccessor(sentenceParser);
                sentenceParser.setSuccessor(wordParser);
        		
        		process("parsing text");
        		CompositeObject wholeText = (CompositeObject)textParser.parse(text);
        		LOG.info("success\n");
        		
        		process("extracting parsed text");
            	TextExtractor extractor = new TextExtractor();
            	ofw.writeText("parsed_text.txt", extractor.ExtractText(wholeText));
            	LOG.info("success\n");
            	
            	process("resoring text");
            	TextRestorer textRestorer = new TextRestorer();
            	String restored = textRestorer.restore(ifr.readText("parsed_text.txt")); 
            	ofw.writeText("output.txt", restored);
            	LOG.info("success\n");
            	
            	String result = ifr.readText("output.txt");
            	LOG.info("\nOriginal text equals restored text: " + text.equals(result) + "\n");
            	LOG.info("\noriginal text: input.txt\n"
            			+ "  parsed text: parsed_text.txt\n"
            			+ "restored text: output.txt\n");
            } catch (IOException e) {
            	LOG.info("I/O exception is occured");
            	LOG.error("I/O exception", e);
            	return;
            } catch (InterruptedException e) {
            	LOG.info("Exception in the main thread is occured");
            	LOG.error("Interrupted exception", e);
            	return;
            }
        } else {
        	LOG.info("there is nothing to parse\n");
        }

        LOG.info("\n");
        
        String text2 = "";
        try {
        	process("Reading palindrome.txt");
        	text2 = ifr.readText("palindrome.txt");
        	LOG.info("success\n");
        	
        	process("looking for palindromes");
        	Finder finder = new Finder();
        	String palindrome = finder.findLongestPalindrome(text2);
        	LOG.info("success\n");
        	LOG.info("longest palindrome: [" + palindrome + "]");
        } catch (IOException e) {
        	LOG.info("I/O exception occured");
        	LOG.error("I/O exception", e);
        } catch (InterruptedException e) {
        	LOG.info("Exception in the main thread is occured");
        	LOG.error("Interrupted exception", e);
        }
    }
    
    static void process(String process) throws InterruptedException {
    	LOG.info(String.format("%23s", process));
    	for (int i = 0; i < 10; i++) {
    		LOG.info(".");
    		Thread.sleep(50);
    	}
    }
}

/*
 * class: ParagraphParser
 */

package by.epam.training.service.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.training.model.CompositeObject;
import by.epam.training.model.IComposite;
import by.epam.training.model.Leaf;

/**
 * Class ParagraphParser serves for parsing text line to separate sentences
 * 
 * @version 1.0 02 Aug 2018
 * @author  Maxim Burishinets
 */
public class ParagraphParser extends Parser {
	
	private static final String SENTENCE = "([^.!?]+)(\\.|!|\\?|\\s)*";
	
	public IComposite parse(String paragraph) {
		CompositeObject compositeParagraph = new CompositeObject();
        Pattern pattern = Pattern.compile(SENTENCE);
        Matcher matcher = pattern.matcher(paragraph);
        String current = "";
        while (matcher.find()) {
            current = matcher.group();
            if (successor != null) {
            	compositeParagraph.add(successor.parse(current));
            } else {
            	compositeParagraph.add(new Leaf(current));
            }
        }
        return compositeParagraph;
	}

}

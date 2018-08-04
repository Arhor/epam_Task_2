package by.epam.training.service.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.training.model.CompositeObject;
import by.epam.training.model.IComposite;

public class ParagraphParser extends Parser {
	
	private static final String SENTENCE = "([^.!?]+)(\\.|!|\\?|\\s)*";
	
	public IComposite parse(String paragraph) {
		CompositeObject compositeParagraph = new CompositeObject();
        Pattern pattern = Pattern.compile(SENTENCE);
        Matcher matcher = pattern.matcher(paragraph);
        String current = "";
        while (matcher.find()) {
            current = matcher.group();
            compositeParagraph.add(successor.parse(current));
        }
        return compositeParagraph;
	}

}

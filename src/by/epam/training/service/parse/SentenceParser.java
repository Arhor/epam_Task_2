/*
 * class: SentenceParser
 */

package by.epam.training.service.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.training.model.CompositeObject;
import by.epam.training.model.IComposite;
import by.epam.training.model.Leaf;

/**
 * Class SentenceParser serves for parsing text line to separate
 * words and delimiters
 * 
 * @version 1.0 02 Aug 2018
 * @author  Maxim Burishinets
 */
public class SentenceParser extends Parser {
	
	private static final String WORD_OR_DELIMITER =
            "([A-Za-z0-9]+)|([^A-Za-z0-9])";
    private static final String WORD = "[A-Za-z0-9]+";

    /**
     * The method looks for matches WORD_OR_DELIMITER pattern in the given line
     * and if it matches passes to successor's 'parse' method, then it's added
     * to Composite object as another Composite object, else it's added as a
     * Leaf object (represents delimiter). If successor is not set - it is added as Leaf.
     */
	public IComposite parse(String sentence) {
		CompositeObject compositeSentence = new CompositeObject();
        Pattern pattern = Pattern.compile(WORD_OR_DELIMITER);
        Matcher matcher = pattern.matcher(sentence);
        String current = "";
        while (matcher.find()) {
            current = matcher.group();
            if (!current.matches(WORD) || successor == null) {
                compositeSentence.add(new Leaf(current));
            } else {
                compositeSentence.add(successor.parse(current));
            }
        }
        return compositeSentence;
	}
}

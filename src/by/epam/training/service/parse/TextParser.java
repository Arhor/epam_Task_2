/*
 * class: TextParser
 */

package by.epam.training.service.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.training.model.CompositeObject;
import by.epam.training.model.IComposite;
import by.epam.training.model.Leaf;

/**
 * Class TextParser serves for parsing whole text to separate
 * paragraphs and code-listings
 * 
 * @version 1.0 02 Aug 2018
 * @author  Maxim Burishinets
 */
public class TextParser extends Parser {

    private static final String TEXT_LINE = "((.+)(\\s?))|(\\s+)";
    private static final String LISTING_START = "(\\\\~)(.*)(\\s?)";
    private static final String LISTING_END = "(\\s*)(.*)(~\\\\)(\\s?)";
    private static final String ONE_LINE_LISTING =
            "(\\s*)(\\\\~)(.+)(~\\\\)(\\s?)";

    /**
     * The method runs through the String line by line. If the line matches
     * the LISTING_START pattern flag-variable isListing switches to 'true'
     * and each next line considered as code-listing until it matches the 
     * LISTING_END pattern, then isListing switches to 'false' and whole
     * code-listing is added to Composite object as Leaf object. Else line
     * considered as paragraph and passed to successor's method 'parse' then
     * added to Composite object as another Composite object. If successor is
     * not set - paragraph is added as a Leaf object.
     */
    public IComposite parse(String text) {
        CompositeObject compositeText = new CompositeObject();
        Pattern pattern = Pattern.compile(TEXT_LINE);
        Matcher matcher = pattern.matcher(text);
        StringBuilder sb = new StringBuilder();
        String current = "";
        boolean isListing = false;
        while (matcher.find()) {
            current = matcher.group();
            sb.append(current);
            if (!current.matches(LISTING_START) && !isListing
                    && !current.matches(ONE_LINE_LISTING)) {
                if (successor != null) {
                    compositeText.add(successor.parse(current));
                } else {
                    compositeText.add(new Leaf(current));
            	}
                sb = new StringBuilder();
            } else if (current.matches(LISTING_START) && !isListing
                    && !current.matches(ONE_LINE_LISTING)) {
                isListing = true;
            } else if ((current.matches(LISTING_END) && isListing)
                    || current.matches(ONE_LINE_LISTING)) {
                isListing = false;
                compositeText.add(new Leaf(sb.toString()));
                sb = new StringBuilder();
            }
        }
        return compositeText;
    }
}

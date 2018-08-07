/*
 * class: TextRestorer
 */

package by.epam.training.service.restore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class TextRestorer helps to restore previously parsed text
 * 
 * @version 1.0 02 Aug 2018
 * @author  Maxim Burishinets
 */
public class TextRestorer {

    private static final String TEXT_LINE = "(\\s*)(.+)(\\s?)";
    private static final String PARAGRAPH =
            "\\s*\\[Paragraph\\]: \\[(\\w|\\W)*\\s";
    private static final String LISTING_START =
            "\\s*\\[Listing\\]: \\[(\\s*)\\\\~(.*)\\s*";
    private static final String LISTING_END =
            "(\\s*)(.*)~\\\\((\\s*)\\])?(\\s*)";

    /**
     * The method runs through the parsed text line by line and takes only
     * [paragraph] and [listing] blocks
     * 
     * @param parsedText a String extracted from Composite object
     * @return restored text in original form
     */
    public String restore(String parsedText) {
        Pattern pattern = Pattern.compile(TEXT_LINE);
        Matcher matcher = pattern.matcher(parsedText);
        StringBuilder sb = new StringBuilder();
        boolean isListing = false;
        String current;
        while (matcher.find()) {
            current = matcher.group();
            if (current.matches(PARAGRAPH)) {
                sb.append(current.replaceAll(
                        "(\\s*\\[Paragraph\\]: \\[)|(]\\s)", ""));
                continue;
            } else if (current.matches(LISTING_START)) {
                isListing = true;
                current = current.replaceAll(
                        "\\s*\\[Listing\\]: \\[(\\s*)","");
            }
            if (current.matches(LISTING_END) && isListing) {
                isListing = false;
                sb.append(current.replaceAll(
                        "(~\\\\)(\\])(\\s)", "~\\\\"));
            } else if (isListing) {
                sb.append(current);
            }
        }
        return sb.toString();
    }
}
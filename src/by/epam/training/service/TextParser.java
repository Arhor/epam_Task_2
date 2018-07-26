/*
 * class: TextParser
 *
 * version: 1.0 26 Jul 2018
 *
 * author: Maxim Burishinets
 */


package by.epam.training.service;

import by.epam.training.model.IComposite;
import by.epam.training.model.CompositeObject;
import by.epam.training.model.Leaf;

public abstract class TextParser {

    /*
     * method takes a string that represents whole text, splits it to separate
     * paragraphs and code-listing blocks (text blocks between /~ and ~/ signs)
     * then add them to composite-object text (composite paragraphs and leaf
     * listing-blocks) and returns it
     */
    public static IComposite parseToParagraphs(String text) {
        IComposite compositeText = new CompositeObject();
        StringBuilder sb = new StringBuilder();
        boolean isListing = false;
        boolean listingPresent = false;
        for (int i = 0; i < text.length(); i++) {
            String currentSymbol = text.substring(i, i + 1);
            sb.append(currentSymbol);
            if (currentSymbol.equals("\\") && !isListing && sb.length() == 1) {
                if (i < text.length() - 1
                        && text.substring(i, i + 2).equals("\\~")) {
                    isListing = true;
                }
            } else if (isListing && currentSymbol.equals("~")){
                if (i < text.length() - 1
                        && text.substring(i, i + 2).equals("~\\")) {
                    isListing = false;
                    listingPresent = true;
                }
            }
            // TODO: fix below - method doesn't add listing without "~/" sign
            if (!isListing) {
                if (currentSymbol.equals("\n") || i == text.length() - 1) {
                    if (listingPresent) {
                        compositeText.add(new Leaf(sb.toString()));
                        listingPresent = false;
                    } else {
                        compositeText.add(parseToSentences(sb.toString()));
                    }
                    sb = new StringBuilder();
                }
            }
        }
        return compositeText;
    }

    /*
     * method takes a string that represents a single paragraph and split it
     * to separate sentences, adds them as composite-objects to composite-
     * object paragraph and returns it
     */

    private static IComposite parseToSentences(String paragraph) {
        IComposite compositeParagraph = new CompositeObject();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paragraph.length(); i++) {
            String currentSymbol = paragraph.substring(i, i + 1);
            sb.append(currentSymbol);
            if (i == paragraph.length() - 1
                    || (currentSymbol.matches("[.?!]")
                    && paragraph.substring(i + 1, i + 2).matches("[^.?!]"))) {
                    compositeParagraph.add(parseToWords(sb.toString()));
                    sb = new StringBuilder();
            }
        }
        return compositeParagraph;
    }

    /*
     * method takes a string that represents a single sentence and splits it to
     * separate words and delimiters, then method adds words as composite-
     * objects and delimiters as leaf-objects to composite-object sentence
     * and returns it
     */
    private static IComposite parseToWords(String sentence) {
        IComposite compositeSentence = new CompositeObject();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            String currentSymbol = sentence.substring(i, i + 1);
            if (currentSymbol.matches("[A-Za-z0-9]")) {
                sb.append(currentSymbol);
            } else {
                compositeSentence.add(new Leaf(currentSymbol)); // delimiter
            }
            if (
                    sb.length() != 0
                    &&(i == sentence.length() - 1
                    || sentence.substring(i + 1, i + 2).matches("[^A-Za-z0-9]"))
               ) {
                compositeSentence.add(parseToChars(sb.toString())); // word
                sb = new StringBuilder();
            }
        }
        return compositeSentence;
    }

    /*
     * method takes a sequence of letters that represents a word and splits it
     * to separate characters, each of them is added to IComposite-object word
     * as a Leaf-object, then method returns IComposite-object word
     */
    private static IComposite parseToChars(String word) {
        IComposite compositeWord = new CompositeObject();
        for (char character : word.toCharArray()) {
            String content = String.valueOf(character);
            compositeWord.add(new Leaf(content));
        }
        return compositeWord;
    }
}

/*
 *
 */


package by.epam.training.service;

import by.epam.training.model.IComposite;
import by.epam.training.model.CompositeObject;
import by.epam.training.model.Leaf;

public abstract class TextParser {

    public static IComposite parseToSentences(String text) {
        // TODO: implement parser to sentences
        return null; //stub
    }

    /*
     * method takes string that represents a single sentence and splits it to
     * separate words and delimiters, then method adds words as composite-
     * objects and delimiters as leaf-objects to composite-object "sentence"
     * and returns it
     */
    public static IComposite parseToWords(String sentence) {
        IComposite compositeSentence = new CompositeObject();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            String currentSymbol = sentence.substring(i, i + 1);
            if (currentSymbol.matches("[A-Za-z]")) {
                sb.append(currentSymbol);
            } else {
                compositeSentence.add(new Leaf(currentSymbol)); // delimiter
            }
            if (
                    sb.length() != 0
                    &&(i == sentence.length() - 1
                    || sentence.substring(i + 1, i + 2).matches("[^A-Za-z]"))
               ) {
                compositeSentence.add(parseToChars(sb.toString())); // word
                sb = new StringBuilder();
            }
        }
        return compositeSentence;
    }

    /*
     * method takes sequence of letters that represents a word and splits it to
     * separate characters, each of them is added to IComposite-object "word" as
     * a Leaf-object, then method returns IComposite-object "word"
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

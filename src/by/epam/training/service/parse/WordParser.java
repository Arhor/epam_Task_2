/*
 * class: WordParser
 */

package by.epam.training.service.parse;

import by.epam.training.model.CompositeObject;
import by.epam.training.model.IComposite;
import by.epam.training.model.Leaf;

/**
 * Class WordParser serves for parsing single word to separate characters
 * 
 * @version 1.0 02 Aug 2018
 * @author  Maxim Burishinets
 */
public class WordParser extends Parser {

    /**
     * The method runs through char array that represents given String and
     * and each character is added to Composite object as Leaf object
     */
    public IComposite parse(String word) {
        CompositeObject compositeWord = new CompositeObject();
        for (char character : word.toCharArray()) {
            String current = String.valueOf(character);
            compositeWord.add(new Leaf(current));
        }
        return compositeWord;
    }
}

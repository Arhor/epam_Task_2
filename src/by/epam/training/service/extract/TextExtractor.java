/*
 * class: TextExtractor
 */

package by.epam.training.service.extract;

import by.epam.training.model.*;

/**
 * Class TextExtractor serves for extracting text in parsed form from
 * composite-object
 * 
 * @version 1.0 01 Aug 2018
 * @author  Maxim Burishinets
 */
public class TextExtractor {

    /**
     * Extracts text from a Composite object.
     *
     * @param wholeText Composite object that represents parsed text in a tree structure
     * @return String of text in parsed form
     */
    public String ExtractText(CompositeObject wholeText) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wholeText.size(); i++) {
            IComposite levelOne = wholeText.get(i);
            if (levelOne instanceof Leaf) {
                sb.append("[Listing]: [" + levelOne.print() + "]\r\n");
            } else {
                sb.append("[Paragraph]: [" + levelOne.print() + "]\r\n");
                for (int j = 0; j < ((CompositeObject)levelOne).size(); j++) {
                    CompositeObject levelTwo = (CompositeObject)levelOne.get(j);
                    sb.append(" [Sentence]: [" + levelTwo.print() + "]\r\n");
                    for (int k = 0; k < levelTwo.size(); k++) {
                        IComposite levelThree = levelTwo.get(k);
                        if (levelThree instanceof Leaf) {
                            sb.append("[Delimiter]: [" + levelThree.print() + "]\r\n");
                        } else {
                            sb.append("     [Word]: [" + levelThree.print() + "]\r\n");
                            for (int z = 0; z < ((CompositeObject)levelThree).size(); z++) {
                                IComposite levelFour = levelThree.get(z);
                                sb.append("[character]: [" + levelFour.print() + "]\r\n");
                            }
                        }
                    }
                }
            }
        }
        return sb.toString();
    }
}

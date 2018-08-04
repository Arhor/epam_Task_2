package by.epam.training.service.extract;

import by.epam.training.model.CompositeObject;
import by.epam.training.model.IComposite;
import by.epam.training.model.Leaf;

public class TextExtractor {

	public String ExtractText(IComposite wholeText) {
        StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < ((CompositeObject)wholeText).size(); i++) {
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

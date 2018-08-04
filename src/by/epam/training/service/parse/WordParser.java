package by.epam.training.service.parse;

import by.epam.training.model.CompositeObject;
import by.epam.training.model.IComposite;
import by.epam.training.model.Leaf;

public class WordParser extends Parser {

	public IComposite parse(String word) {
		CompositeObject compositeWord = new CompositeObject();
        for (char character : word.toCharArray()) {
            String current = String.valueOf(character);
            compositeWord.add(new Leaf(current));
        }
        return compositeWord;
	}
}

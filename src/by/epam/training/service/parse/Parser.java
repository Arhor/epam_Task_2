package by.epam.training.service.parse;

import by.epam.training.model.IComposite;

public abstract class Parser {
	
	protected Parser successor;
	
	public void setSuccessor(Parser successor) {
		this.successor = successor;
	}
	
	public abstract IComposite parse(String str);
}

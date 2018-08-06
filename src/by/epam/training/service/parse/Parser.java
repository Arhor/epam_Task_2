/*
 * class: Parser
 */

package by.epam.training.service.parse;

import by.epam.training.model.IComposite;

/**
 * Class Parser serves as superclass for every parser class and helps
 * to implement chain-of-responsibility programming pattern
 * 
 * @version 1.0 02 Aug 2018
 * @author  Maxim Burishinets
 */
public abstract class Parser {
	
	protected Parser successor;
	
	public void setSuccessor(Parser successor) {
		this.successor = successor;
	}
	
	public abstract IComposite parse(String str);
}

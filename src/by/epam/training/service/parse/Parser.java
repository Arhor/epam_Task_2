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
	
    /**
     * Parses text depending on implementation of the method in concrete class
     * 
     * @param text a String to parse
     * @return Composite object that contains parsed text
     */
	public abstract IComposite parse(String str);
}

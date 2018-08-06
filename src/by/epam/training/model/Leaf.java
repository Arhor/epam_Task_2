/*
 * class: Leaf
 */

package by.epam.training.model;

/**
 * Class Leaf serves for instantiation Leaf objects in composite structure
 * 
 * @version 1.0 21 Jul 2018
 * @author  Maxim Burishinets
 */
public class Leaf implements IComposite {

    private String content;

    /**
     * Constructs a newly allocated Leaf object for composite structure
     * 
     * @param content the String to be stored in Leaf object
     */
    public Leaf(String content) {
        this.content = content;
    }

    public IComposite get() {
        return this;
    }

    /**
     * Prints String contained in this Leaf object
     * 
     * @return String content of Leaf object
     */
    @Override
    public String print() {
        return this.content;
    }
}

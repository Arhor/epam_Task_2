/*
 * Class: IComposite
 */

package by.epam.training.model;

/**
 * IComposite serves as interface for CompositeObject and Leaf classes
 * and helps to implement composite programming pattern
 * 
 * @version 1.0 20 Jul 2018
 * @author  Maxim Burishinets
 */
public interface IComposite {

    String print();
    
    IComposite get(int i);
}

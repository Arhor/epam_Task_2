/*
 * class: CompositeObject
 */

package by.epam.training.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class CompositeObject serves for instantiation Composite objects, that
 * can contain Leaf objects or other Composite objects
 * 
 * @version 1.0 21 Jul 2018
 * @author  Maxim Burishinets
 */
public class CompositeObject implements IComposite {

	/** List of Leafs and Composite objects in current Composite object */
    private List<IComposite> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public void add(IComposite compositeObject) {
        list.add(compositeObject);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public IComposite get(int index) {
        return list.get(index);
    }

    /**
     * Prints String that contains each Leaf object in this Composite object
     * 
     * @return String content of each object in this structure
     */
    @Override
    public String print() {
        StringBuilder innerContent = new StringBuilder();
        for (IComposite compositeObject : list) {
            innerContent.append(compositeObject.print());
        }
        return innerContent.toString();
    }
}

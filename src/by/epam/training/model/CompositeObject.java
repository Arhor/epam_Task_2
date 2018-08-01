/*
 * class: CompositeObject
 *
 * version: 1.0 21 Jul 2018
 *
 * author: Maxim Burishinets
 */

package by.epam.training.model;

import java.util.ArrayList;
import java.util.List;

public class CompositeObject implements IComposite {

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

    @Override
    public String print() {
        StringBuilder innerContent = new StringBuilder();
        for (IComposite compositeObject : list) {
            innerContent.append(compositeObject.print());
        }
        return innerContent.toString();
    }
}

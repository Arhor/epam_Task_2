package by.epam.training.model;

import java.util.ArrayList;
import java.util.List;

public class CompositeObject implements IComposite {

    List<IComposite> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    @Override
    public void add(IComposite compositeObject) {
        list.add(compositeObject);
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    public IComposite get(int index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        StringBuilder innerContent = new StringBuilder();
        for (IComposite compositeObject : list) {
            innerContent.append(compositeObject.toString());
        }
        return innerContent.toString();
    }
}

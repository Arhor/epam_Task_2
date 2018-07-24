package by.epam.training.model;

import java.util.ArrayList;
import java.util.List;

public class CompositeObject implements Composite {

    List<Composite> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    @Override
    public void add(Composite compositeObject) {
        list.add(compositeObject);
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    public Composite get(int index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        StringBuilder innerContent = new StringBuilder();
        for (Composite compositeObject : list) {
            innerContent.append(compositeObject.toString());
        }
        return innerContent.toString();
    }
}

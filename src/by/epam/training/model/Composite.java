package by.epam.training.model;

public interface Composite {

    void add(Composite compositeObject);

    void remove(int index);

    Composite get(int index);
}

package by.epam.training.model;

public interface IComposite {

    void add(IComposite compositeObject);

    void remove(int index);

    IComposite get(int index);
}

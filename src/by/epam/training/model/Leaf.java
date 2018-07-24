package by.epam.training.model;

public class Leaf implements Composite {

    String content;

    public Leaf(String content) {
        this.content = content;
    }

    @Override
    public void add(Composite compositeObject) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("it's impossible to add to leaf");
    }

    @Override
    public void remove(int index) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("it's impossible to remove from leaf");
    }

    @Override
    public Composite get(int index) {
        return this;
    }

    @Override
    public String toString() {
        return this.content;
    }


}

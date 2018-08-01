/*
 * class: Leaf
 *
 * version: 1.0 21 Jul 2018
 *
 * author: Maxim Burishinets
 */

package by.epam.training.model;

public class Leaf implements IComposite {

    private String content;

    public Leaf(String content) {
        this.content = content;
    }

    @Override
    public IComposite get(int index) {
        return this;
    }

    @Override
    public String print() {
        return this.content;
    }
}

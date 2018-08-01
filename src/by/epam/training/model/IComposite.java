/*
 * Class: IComposite
 *
 * version: 1.0 20 Jul 2018
 *
 * author: Maxim Burishinets
 */

package by.epam.training.model;

public interface IComposite {

    String print();

    IComposite get(int index);
}

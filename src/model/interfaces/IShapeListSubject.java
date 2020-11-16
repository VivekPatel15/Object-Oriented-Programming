package model.interfaces;

import java.util.ArrayList;

public interface IShapeListSubject {

    ArrayList<IShape> getShapes();

    void add(IShape s);

    void addAll(ArrayList<IShape> shapes);

    void remove(IShape s);

    void removeAll(ArrayList<IShape> shapes);

    void copy(IShape shape);

    void delete();

    void clearClipboard();

    ArrayList<IShape> getClipboard();

    void register(IShapeListObserver observer);

    void unsubscribe(IShapeListObserver observer);

    void updateObserver();

}

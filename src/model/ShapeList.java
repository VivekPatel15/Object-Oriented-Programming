package model;

import model.interfaces.IShape;
import model.interfaces.IShapeListObserver;
import model.interfaces.IShapeListSubject;

import java.util.ArrayList;

public class ShapeList implements IShapeListSubject {

    private final ArrayList<IShape> shapes = new ArrayList<>();
    private final ArrayList<IShape> clipboard = new ArrayList<>();
    private final ArrayList<IShape> selectedShapes = new ArrayList<>();
    private final ArrayList<IShapeListObserver> observers = new ArrayList<>();


    public ShapeList(){ }

    public ArrayList<IShape> getShapes() { return shapes; }

    public void add(IShape s){
        shapes.add(s);
        updateObserver();
    }

    public void addAll(ArrayList<IShape> shapesToAdd){
        shapes.addAll(shapesToAdd);
        updateObserver();
    }

    public void remove(IShape s){
        shapes.remove(s);
        updateObserver();
    }

    public void removeAll(ArrayList<IShape> shapesToRemove){
        shapes.removeAll(shapesToRemove);
        updateObserver();
    }

    public void copy(IShape shape){
        switch (shape.getShapeType()){
            case RECTANGLE:
                IShape r = new Rectangle(shape.getTopLeft(), shape.getWidth(), shape.getHeight(), shape.getShapeColorPrimary(), shape.getShapeColorSecondary(), shape.getShadingType());
                clipboard.add(r);
                break;
            case TRIANGLE:
                IShape t = new Triangle(shape.getTopLeft(), shape.getWidth(), shape.getHeight(), shape.getShapeColorPrimary(), shape.getShapeColorSecondary(), shape.getShadingType());
                clipboard.add(t);
                break;
            case ELLIPSE:
                IShape e = new Ellipse(shape.getTopLeft(), shape.getWidth(), shape.getHeight(), shape.getShapeColorPrimary(), shape.getShapeColorSecondary(), shape.getShadingType());
                clipboard.add(e);
                break;
        }
    }

    public void delete(){
        shapes.removeIf(s -> s.isSelected());
        updateObserver();
    }


    public void clearClipboard(){ clipboard.clear(); }

    public ArrayList<IShape> getClipboard() { return clipboard; }

    public void addSelectedShapes(){
        for (IShape shape : shapes){
            if (shape.isSelected()){
                selectedShapes.add(shape);
            }
        }
    }

    public void clearSelectedShapes(){ selectedShapes.clear(); }

    public ArrayList<IShape> getSelectedShapes(){ return selectedShapes; }

    public void register(IShapeListObserver observer) { observers.add(observer); }

    public void unsubscribe(IShapeListObserver observer) { observers.remove(observer); }

    public void updateObserver() {
        for (IShapeListObserver shapeListObserver : observers){
            shapeListObserver.update();
        }
    }
}

package controller;

import controller.interfaces.ICommand;
import model.Point;
import model.ShapeList;
import model.interfaces.IShape;

public class SelectShapeCommand implements ICommand {

    private final ShapeList shapeList;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public SelectShapeCommand(Point start, Point end, ShapeList shapeList) {
        this.shapeList = shapeList;
        x = Math.min(start.getX(), end.getX());
        y = Math.min(start.getY(), end.getY());
        int w = start.getX() - end.getX();
        width = Math.abs(w);
        int h = start.getY() - end.getY();
        height = Math.abs(h);
    }

    @Override
    public void run() {
        deselect();
        for (IShape shape : shapeList.getShapes()){
            if (x < shape.getTopLeft().getX() + shape.getWidth() &&
                x + width > shape.getTopLeft().getX() &&
                y < shape.getTopLeft().getY() + shape.getHeight() &&
                y + height > shape.getTopLeft().getY()){
                shape.select();
            }
        }
        shapeList.addSelectedShapes();
        shapeList.updateObserver();
    }

    private void deselect(){
        for (IShape shape : shapeList.getShapes()){
            if (shape.isSelected()){
                shape.deselect();
            }
        }
        shapeList.clearSelectedShapes();
    }
}

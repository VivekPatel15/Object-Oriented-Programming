package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.Point;
import model.ShapeList;
import model.interfaces.IShape;

public class MoveCommand implements ICommand, IUndoable {

    private final ShapeList shapeList;
    private final int deltaX;
    private final int deltaY;
    private final int reversedx;
    private final int reversedy;


    public MoveCommand(Point start, Point end, ShapeList shapeList) {
        this.shapeList = shapeList;
        this.deltaX = end.getX() - start.getX();
        this.deltaY = end.getY() - start.getY();
        this.reversedx = deltaX * -1;
        this.reversedy = deltaY * -1;
        CommandHistory.add(this);
    }

    @Override
    public void run() {
        for (IShape shape : shapeList.getShapes()){
            if (shape.isSelected()){
                shape.move(deltaX, deltaY);
            }
        }
        shapeList.updateObserver();
    }

    @Override
    public void undo() {
        for (IShape shape : shapeList.getShapes()){
            if (shape.isSelected()){
                shape.move(reversedx, reversedy);
            }
        }
        shapeList.updateObserver();
    }

    @Override
    public void redo() {
        for (IShape shape : shapeList.getShapes()){
            if (shape.isSelected()){
                shape.move(deltaX, deltaY);
            }
        }
        shapeList.updateObserver();
    }
}

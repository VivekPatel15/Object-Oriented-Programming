package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.Point;
import model.ShapeList;
import model.interfaces.IShape;

import java.util.ArrayList;

public class PasteCommand implements ICommand, IUndoable {

    private final ShapeList shapeList;
    private final ArrayList<IShape> offsetShapes = new ArrayList<>();

    public PasteCommand(ShapeList shapeList){
        this.shapeList = shapeList;
        CommandHistory.add(this);
    }

    @Override
    public void run() {
        for (IShape shape : shapeList.getClipboard()){
            Point offset = offset(shape.getTopLeft());
            shape.setTopLeft(offset);
            shapeList.add(shape);
            offsetShapes.add(shape);
        }
    }

    public Point offset(Point p){
        int x = p.getX() + 100;
        int y = p.getY() + 100;
        return new Point(x, y);
    }

    @Override
    public void undo() {
        for (IShape shape : offsetShapes){
            shapeList.remove(shape);
        }
    }

    @Override
    public void redo() {
        for (IShape shape : offsetShapes){
            shapeList.add(shape);
        }
    }
}

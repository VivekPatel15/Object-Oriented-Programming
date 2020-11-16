package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.ShapeList;
import model.interfaces.IShape;
import java.util.ArrayList;

public class DeleteCommand implements ICommand, IUndoable {

    private final ShapeList shapeList;
    private final ArrayList<IShape> tempHolder = new ArrayList<>();


    public DeleteCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        for (IShape shape : shapeList.getShapes()){
            if (shape.isSelected()){
                tempHolder.add(shape);
            }
        }
        shapeList.delete();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for (IShape shape : tempHolder){
            shapeList.add(shape);
            shapeList.updateObserver();
        }
    }

    @Override
    public void redo() {
        shapeList.delete();
    }
}

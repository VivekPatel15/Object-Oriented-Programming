package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.ShapeList;
import model.interfaces.IShape;
import java.util.ArrayList;

public class UngroupCommand implements ICommand, IUndoable {

    private final ShapeList shapeList;
    private final ArrayList<IShape> shapeGroups = new ArrayList<>();
    private final ArrayList<IShape> ungroupedShapes = new ArrayList<>();


    public UngroupCommand(ShapeList shapeList){
        this.shapeList = shapeList;
        for (IShape shape : shapeList.getShapes()){
            if (shape.getShapes().size() > 1){
                this.shapeGroups.add(shape);
            }
        }
        for (IShape group : shapeGroups){
            ungroupedShapes.addAll(group.getShapes());
        }
        CommandHistory.add(this);
    }

    @Override
    public void run() {
        shapeList.removeAll(shapeGroups);
        for (IShape shape : ungroupedShapes){
            shape.select();
        }
        shapeList.addAll(ungroupedShapes);
    }

    @Override
    public void undo() {
        for (IShape shape : ungroupedShapes){
            shape.deselect();
        }
        shapeList.removeAll(ungroupedShapes);
        shapeList.addAll(shapeGroups);
        for (IShape group : shapeGroups){
            group.select();
        }
    }

    @Override
    public void redo() {
        shapeList.removeAll(shapeGroups);
        for (IShape shape : ungroupedShapes){
            shape.select();
        }
        shapeList.addAll(ungroupedShapes);
    }
}

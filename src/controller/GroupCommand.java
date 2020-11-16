package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.ShapeGroup;
import model.ShapeList;
import model.interfaces.IShape;
import java.util.ArrayList;

public class GroupCommand implements ICommand, IUndoable {

    private final ShapeList shapeList;
    private final ArrayList<IShape> groupedShapes = new ArrayList<>();
    private final IShape shapeGroup = new ShapeGroup();

    public GroupCommand(ShapeList shapeList){
        this.shapeList = shapeList;
        groupedShapes.addAll(shapeList.getSelectedShapes());
        shapeGroup.add(groupedShapes);
        CommandHistory.add(this);
    }

    @Override
    public void run() {
        if(shapeList.getSelectedShapes().size() > 1) {
            shapeList.delete();
            for (IShape shape : groupedShapes) {
                shape.deselect();
            }
            shapeList.add(shapeGroup);
        }
        shapeGroup.select();
    }

    @Override
    public void undo() {
        shapeList.remove(shapeGroup);
        for (IShape shape : groupedShapes){
            shape.select();
            shapeList.add(shape);
        }
    }

    @Override
    public void redo() {
        for (IShape shape : groupedShapes) {
            shape.deselect();
        }
        shapeList.removeAll(groupedShapes);
        shapeList.add(shapeGroup);
    }
}

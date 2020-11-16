package controller;

import controller.interfaces.ICommand;
import model.ShapeList;
import model.interfaces.IShape;

public class CopyCommand implements ICommand {

    private final ShapeList shapeList;

    public CopyCommand(ShapeList shapeList){
        this.shapeList = shapeList;
    }

    public void run() {
        shapeList.clearClipboard();
        for (IShape shape : shapeList.getShapes()){
            if (shape.isSelected()){
                shapeList.copy(shape);
            }
        }
    }
}

package controller;

import controller.interfaces.ICommand;
import controller.interfaces.IUndoable;
import model.*;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

public class CreateShapeCommand implements ICommand, IUndoable {
    private final Point topLeft;
    private final ShapeList shapeList;
    private final ApplicationState applicationState;
    private final int width;
    private final int height;
    private final ShapeColor primaryColor;
    private final ShapeColor secondaryColor;
    private final ShapeShadingType shadingType;
    private IShape shape;

    public CreateShapeCommand(Point start, Point end, ShapeList shapeList, ApplicationState applicationState){
        this.shapeList = shapeList;
        int x = Math.min(start.getX(), end.getX());
        int y = Math.min(start.getY(), end.getY());
        topLeft = new Point(x, y);
        int w = start.getX() - end.getX();
        width = Math.abs(w);
        int h = start.getY() - end.getY();
        height = Math.abs(h);
        this.applicationState = applicationState;
        this.primaryColor = applicationState.getActivePrimaryColor();
        this.secondaryColor = applicationState.getActiveSecondaryColor();
        this.shadingType = applicationState.getActiveShapeShadingType();
        CommandHistory.add(this);
    }

    @Override
    public void run() {
        switch (applicationState.getActiveShapeType()){
            case RECTANGLE:
                shape = new Rectangle(topLeft, width, height, primaryColor, secondaryColor, shadingType);
                shapeList.add(shape);
                break;
            case TRIANGLE:
                shape = new Triangle(topLeft, width, height, primaryColor, secondaryColor, shadingType);
                shapeList.add(shape);
                break;
            case ELLIPSE:
                shape = new Ellipse(topLeft, width, height, primaryColor, secondaryColor, shadingType);
                shapeList.add(shape);
                break;
        }
    }

    @Override
    public void undo() { shapeList.remove(shape); }

    @Override
    public void redo() { shapeList.add(shape); }
}

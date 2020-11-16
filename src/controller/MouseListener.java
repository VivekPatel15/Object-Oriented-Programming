package controller;

import model.Point;
import model.ShapeList;
import model.persistence.ApplicationState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    private Point start;
    private final ShapeList shapeList;
    private final ApplicationState applicationState;

    public MouseListener(ShapeList shapes, ApplicationState applicationState){
        shapeList = shapes;
        this.applicationState = applicationState;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        start = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point end = new Point(e.getX(), e.getY());
        //System.out.println(start.toString() + ", " + end.toString());

        switch (applicationState.getActiveMouseMode()){
            case DRAW:
                CreateShapeCommand createShapeCommand = new CreateShapeCommand(start, end, shapeList, applicationState);
                createShapeCommand.run();
                break;
            case MOVE:
                MoveCommand moveCommand = new MoveCommand(start, end, shapeList);
                moveCommand.run();
                break;
            case SELECT:
                SelectShapeCommand selectShapeCommand = new SelectShapeCommand(start, end, shapeList);
                selectShapeCommand.run();
                break;
        }
    }
}

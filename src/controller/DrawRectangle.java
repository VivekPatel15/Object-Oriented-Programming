package controller;

import controller.interfaces.IDrawShape;
import model.interfaces.IShape;
import java.awt.*;

public class DrawRectangle implements IDrawShape {

    private final IShape rectangle;
    private final Graphics2D graphics2D;

    public DrawRectangle(IShape rectangle, Graphics2D graphics2D){
        this.rectangle = rectangle;
        this.graphics2D = graphics2D;
    }

    @Override
    public void draw() {
        switch (rectangle.getShadingType()){
            case OUTLINE:
                graphics2D.setStroke(new BasicStroke(5));
                graphics2D.setColor(rectangle.getPrimaryShapeColor());
                graphics2D.drawRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), rectangle.getWidth(), rectangle.getHeight());
                break;
            case FILLED_IN:
                graphics2D.setColor(rectangle.getPrimaryShapeColor());
                graphics2D.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), rectangle.getWidth(), rectangle.getHeight());
                break;
            case OUTLINE_AND_FILLED_IN:
                graphics2D.setStroke(new BasicStroke(5));
                graphics2D.setColor(rectangle.getPrimaryShapeColor());
                graphics2D.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), rectangle.getWidth(), rectangle.getHeight());
                graphics2D.setColor(rectangle.getSecondaryShapeColor());
                graphics2D.drawRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(), rectangle.getWidth(), rectangle.getHeight());
                break;
        }
    }

    public void outline(IShape shape, Graphics2D graphics2D) { }
}

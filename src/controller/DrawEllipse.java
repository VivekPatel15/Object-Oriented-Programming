package controller;

import controller.interfaces.IDrawShape;
import model.interfaces.IShape;
import java.awt.*;

public class DrawEllipse implements IDrawShape {

    private final IShape ellipse;
    private final Graphics2D graphics2D;

    public DrawEllipse(IShape ellipse, Graphics2D graphics2D){
        this.ellipse = ellipse;
        this.graphics2D = graphics2D;
    }

    @Override
    public void draw() {
        switch (ellipse.getShadingType()){
            case OUTLINE:
                graphics2D.setStroke(new BasicStroke(5));
                graphics2D.setColor(ellipse.getPrimaryShapeColor());
                graphics2D.drawOval(ellipse.getTopLeft().getX(), ellipse.getTopLeft().getY(), ellipse.getWidth(), ellipse.getHeight());
                break;
            case FILLED_IN:
                graphics2D.setColor(ellipse.getPrimaryShapeColor());
                graphics2D.fillOval(ellipse.getTopLeft().getX(), ellipse.getTopLeft().getY(), ellipse.getWidth(), ellipse.getHeight());
                break;
            case OUTLINE_AND_FILLED_IN:
                graphics2D.setStroke(new BasicStroke(5));
                graphics2D.setColor(ellipse.getPrimaryShapeColor());
                graphics2D.fillOval(ellipse.getTopLeft().getX(), ellipse.getTopLeft().getY(), ellipse.getWidth(), ellipse.getHeight());
                graphics2D.setColor(ellipse.getSecondaryShapeColor());
                graphics2D.drawOval(ellipse.getTopLeft().getX(), ellipse.getTopLeft().getY(), ellipse.getWidth(), ellipse.getHeight());
                break;
        }
    }

    public void outline(IShape shape, Graphics2D graphics2D) { }
}

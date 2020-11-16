package controller;

import controller.interfaces.IDrawShape;
import model.interfaces.IShape;
import java.awt.*;

public class DrawTriangle implements IDrawShape {

    private final IShape triangle;
    private final Graphics2D graphics2D;

    public DrawTriangle(IShape triangle, Graphics2D graphics2D){
        this.triangle = triangle;
        this.graphics2D = graphics2D;
    }

    @Override
    public void draw() {

        int[] x = new int[3];
        x[0] = triangle.getTopLeft().getX();
        x[1] = triangle.getTopLeft().getX() + triangle.getWidth();
        x[2] = triangle.getTopLeft().getX() + triangle.getWidth();
        int[] y = new int[3];
        y[0] = triangle.getTopLeft().getY();
        y[1] = triangle.getTopLeft().getY() + triangle.getHeight();
        y[2] = triangle.getTopLeft().getY();

        switch (triangle.getShadingType()){
            case OUTLINE:
                graphics2D.setStroke(new BasicStroke(5));
                graphics2D.setColor(triangle.getPrimaryShapeColor());
                graphics2D.drawPolygon(x, y, 3);
                break;
            case FILLED_IN:
                graphics2D.setColor(triangle.getPrimaryShapeColor());
                graphics2D.fillPolygon(x, y, 3);
                break;
            case OUTLINE_AND_FILLED_IN:
                graphics2D.setStroke(new BasicStroke(5));
                graphics2D.setColor(triangle.getPrimaryShapeColor());
                graphics2D.fillPolygon(x, y, 3);
                graphics2D.setColor(triangle.getSecondaryShapeColor());
                graphics2D.drawPolygon(x, y, 3);
                break;
        }
    }

    public void outline(IShape shape, Graphics2D graphics2D) { }
}

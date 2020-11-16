package controller;

import controller.interfaces.IDrawShape;
import model.Point;
import model.interfaces.IShape;
import java.awt.*;

public class SelectedShapeDecorator extends ShapeDecorator {

    public SelectedShapeDecorator(IDrawShape drawShape){
        super(drawShape);
    }

    /*
    public SelectedShapeDecorator(IShape shape, PaintCanvasBase paintCanvasBase){
        this.shape = shape;
        this.paintCanvasBase = paintCanvasBase;
        this.graphics2D = paintCanvasBase.getGraphics2D();
        p = new Point((shape.getTopLeft().getX() - 5), (shape.getTopLeft().getY() - 5));
        w = shape.getWidth() + 10;
        h = shape.getHeight() + 10;
    }
     */

    public void outline(IShape shape, Graphics2D graphics2D) {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
        Point p = new Point((shape.getTopLeft().getX() - 5), (shape.getTopLeft().getY() - 5));
        int w = shape.getWidth() + 10;
        int h = shape.getHeight() + 10;
        switch (shape.getShapeType()){
            case RECTANGLE:
                graphics2D.drawRect(p.getX(), p.getY(), w, h);
                break;
            case TRIANGLE:
                int[] xs = new int[3];
                int[] ys = new int[3];
                xs[0] = shape.getTopLeft().getX();
                xs[1] = shape.getTopLeft().getX() + shape.getWidth();
                xs[2] = shape.getTopLeft().getX() + shape.getWidth();
                ys[0] = shape.getTopLeft().getY();
                ys[1] = shape.getTopLeft().getY() + shape.getHeight();
                ys[2] = shape.getTopLeft().getY();
                graphics2D.drawPolygon(xs, ys, 3);
                break;
            case ELLIPSE:
                graphics2D.drawOval(p.getX(), p.getY(), w, h);
                break;
        }
    }
}

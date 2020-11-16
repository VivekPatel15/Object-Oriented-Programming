package model;

import controller.DrawEllipse;
import controller.interfaces.IDrawShape;
import controller.SelectedShapeDecorator;

import java.awt.*;

public class Ellipse extends Shape {

    public Ellipse(Point bottomLeft, int width, int height, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType) {
        super(bottomLeft, width, height, primaryColor, secondaryColor, shadingType);
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.ELLIPSE;
    }

    @Override
    public void draw(Graphics2D graphics2D){
        IDrawShape drawEllipse = new SelectedShapeDecorator(new DrawEllipse(this, graphics2D));
        drawEllipse.draw();
        if (isSelected()){ drawEllipse.outline(this, graphics2D); }
    }
}

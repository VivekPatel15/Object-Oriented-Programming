package model;

import controller.DrawTriangle;
import controller.interfaces.IDrawShape;
import controller.SelectedShapeDecorator;

import java.awt.*;

public class Triangle extends Shape {

    public Triangle(Point bottomLeft, int width, int height, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType) {
        super(bottomLeft, width, height, primaryColor, secondaryColor, shadingType);
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.TRIANGLE;
    }

    @Override
    public void draw(Graphics2D graphics2D){
        IDrawShape drawTriangle = new SelectedShapeDecorator(new DrawTriangle(this, graphics2D));
        drawTriangle.draw();
        if (isSelected()){ drawTriangle.outline(this, graphics2D); }
    }
}

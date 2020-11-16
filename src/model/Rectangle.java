package model;

import controller.DrawRectangle;
import controller.interfaces.IDrawShape;
import controller.SelectedShapeDecorator;

import java.awt.*;

public class Rectangle extends Shape {

    public Rectangle(Point topLeft, int width, int height, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType){
        super(topLeft, width, height, primaryColor, secondaryColor, shadingType);
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.RECTANGLE;
    }

    @Override
    public void draw(Graphics2D graphics2D){
        IDrawShape drawRectangle = new SelectedShapeDecorator(new DrawRectangle(this, graphics2D));
        drawRectangle.draw();
        if (isSelected()){ drawRectangle.outline(this, graphics2D); }
    }
}

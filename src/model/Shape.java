package model;


import model.interfaces.IShape;
import java.awt.*;
import java.util.ArrayList;

public class Shape implements IShape {
    private Point topLeft;
    private final int width;
    private final int height;
    private final ShapeColor primaryColor;
    private final ShapeColor secondaryColor;
    private final ShapeShadingType shadingType;
    private boolean selected = false;

    public Shape(Point topLeft, int width, int height, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType){
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shadingType = shadingType;
    }

    public Point getTopLeft(){ return topLeft; }

    public void setTopLeft(Point newBottomLeft) { this.topLeft = newBottomLeft; }

    public int getWidth() { return width; }

    public int getHeight() {
        return height;
    }

    public ShapeType getShapeType() {
        return null;
    }

    public Color getPrimaryShapeColor() { return ColorMap.getColor(primaryColor); }

    public Color getSecondaryShapeColor() { return ColorMap.getColor(secondaryColor); }

    public ShapeColor getShapeColorPrimary() { return primaryColor; }

    public ShapeColor getShapeColorSecondary() { return secondaryColor; }

    public ShapeShadingType getShadingType() { return shadingType; }

    public void move(int dx, int dy){
        this.topLeft = new Point(topLeft.getX() + dx, topLeft.getY() + dy);
    }

    public boolean isSelected() { return selected; }

    public void select() { this.selected = true; }

    public void deselect() { this.selected = false; }

    public void draw(Graphics2D graphics2D){ }

    public void add(ArrayList<IShape> shapes) { System.out.println("I'm afraid I can't let you do that."); }

    public ArrayList<IShape> getShapes() {
        ArrayList<IShape> why = new ArrayList<>();
        why.add(this);
        return why;
    }
}

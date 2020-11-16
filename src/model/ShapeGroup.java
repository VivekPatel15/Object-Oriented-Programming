package model;

import controller.DrawRectangle;
import controller.SelectedShapeDecorator;
import controller.interfaces.IDrawShape;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ShapeGroup implements IShape {

    private final ArrayList<IShape> groupedShapes = new ArrayList<>();
    private Point topLeft;
    private Point bottomRight;
    private int width;
    private int height;
    private boolean selected = true;

    public ShapeGroup(){
        this.topLeft = new Point(9999, 9999);
        this.bottomRight = new Point(0, 0);
    }

    @Override
    public Point getTopLeft() {
        return topLeft;
    }

    @Override
    public void setTopLeft(Point newTopLeft) {
        this.topLeft = newTopLeft;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.RECTANGLE;
    }

    @Override
    public Color getPrimaryShapeColor() {
        return ColorMap.getColor(ShapeColor.BLACK);
    }

    @Override
    public Color getSecondaryShapeColor() {
        return ColorMap.getColor(ShapeColor.WHITE);
    }

    @Override
    public ShapeColor getShapeColorPrimary() {
        return ShapeColor.BLACK;
    }

    @Override
    public ShapeColor getShapeColorSecondary() {
        return ShapeColor.WHITE;
    }

    @Override
    public ShapeShadingType getShadingType() {
        return ShapeShadingType.OUTLINE;
    }

    @Override
    public void move(int dx, int dy){
        for (IShape shape: groupedShapes){
            shape.move(dx, dy);
        }
        this.topLeft = new Point(topLeft.getX() + dx, topLeft.getY() + dy);
        this.bottomRight = new Point(bottomRight.getX() + dx, bottomRight.getY() + dy);
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void select() {
        this.selected = true;
    }

    @Override
    public void deselect() {
        this.selected = false;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        for (IShape shape : groupedShapes){
            shape.draw(graphics2D);
        }
        if (isSelected()){
            IDrawShape drawShape = new SelectedShapeDecorator(new DrawRectangle(this, graphics2D));
            drawShape.outline(this, graphics2D);
        }
    }

    @Override
    public ArrayList<IShape> getShapes() {
        return groupedShapes;
    }

    public void add(ArrayList<IShape> shapes){
        groupedShapes.addAll(shapes);
        resize();
    }

    private void resize(){
        ArrayList<Integer> tlxs = new ArrayList<>();
        ArrayList<Integer> tlys = new ArrayList<>();
        ArrayList<Integer> brxs = new ArrayList<>();
        ArrayList<Integer> brys = new ArrayList<>();
        for (IShape shape : groupedShapes){
            tlxs.add(shape.getTopLeft().getX());
            tlys.add(shape.getTopLeft().getY());
            brxs.add(shape.getTopLeft().getX() + shape.getWidth());
            brys.add(shape.getTopLeft().getY() + shape.getHeight());
        }
        this.topLeft = new Point(Collections.min(tlxs), Collections.min(tlys));
        this.bottomRight = new Point(Collections.max(brxs), Collections.max(brys));
        this.width = bottomRight.getX() - topLeft.getX();
        this.height = bottomRight.getY() - topLeft.getY();
    }
}

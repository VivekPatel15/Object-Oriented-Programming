package model.interfaces;

import model.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import java.awt.*;
import java.util.ArrayList;

public interface IShape {

    Point getTopLeft();

    void setTopLeft(Point newBottomLeft);

    int getWidth();

    int getHeight();

    ShapeType getShapeType();

    Color getPrimaryShapeColor();

    Color getSecondaryShapeColor();

    ShapeColor getShapeColorPrimary();

    ShapeColor getShapeColorSecondary();

    ShapeShadingType getShadingType();

    void move(int dx, int dy);

    boolean isSelected();

    void select();

    void deselect();

    void draw(Graphics2D graphics2D);

    void add(ArrayList<IShape> shapes);

    ArrayList<IShape> getShapes();

}

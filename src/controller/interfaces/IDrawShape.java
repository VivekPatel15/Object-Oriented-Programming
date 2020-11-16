package controller.interfaces;

import model.interfaces.IShape;

import java.awt.*;

public interface IDrawShape {

    void draw();

    void outline(IShape shape, Graphics2D graphics2D);
}

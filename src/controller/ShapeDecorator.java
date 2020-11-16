package controller;

import controller.interfaces.IDrawShape;

public abstract class ShapeDecorator implements IDrawShape {

    private final IDrawShape drawShape;

    public ShapeDecorator(IDrawShape drawShape){
        this.drawShape = drawShape;
    }

    public void draw(){
        drawShape.draw();
    }
}

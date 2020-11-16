package controller;

import model.ShapeList;
import model.interfaces.IShape;
import model.interfaces.IShapeListObserver;
import view.interfaces.PaintCanvasBase;
import java.awt.*;

public class ShapeDrawer implements IShapeListObserver {

    private final ShapeList shapeList;
    private final PaintCanvasBase paintCanvasBase;
    private final Graphics2D graphics2D;

    public ShapeDrawer(ShapeList shapeList, PaintCanvasBase paintCanvasBase){
        this.shapeList = shapeList;
        this.paintCanvasBase = paintCanvasBase;
        this.graphics2D = paintCanvasBase.getGraphics2D();
        shapeList.register(this);
    }

    public void update() {
        clear();
        for (IShape shape : shapeList.getShapes()){
            shape.draw(graphics2D);
        }
    }

    private void clear(){
        graphics2D.setColor(paintCanvasBase.getBackground());
        graphics2D.fillRect(0, 0, paintCanvasBase.getWidth(), paintCanvasBase.getHeight());
    }
}

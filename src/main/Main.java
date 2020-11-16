package main;

import controller.interfaces.IJPaintController;
import controller.JPaintController;
import controller.MouseListener;
import controller.ShapeDrawer;
import model.ShapeList;
import model.interfaces.IShapeListObserver;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        ShapeList masterShapeList = new ShapeList();
        MouseListener mouseListener = new MouseListener(masterShapeList, appState);
        IJPaintController controller = new JPaintController(uiModule, appState, masterShapeList);
        IShapeListObserver shapeDrawer = new ShapeDrawer(masterShapeList, paintCanvas);
        controller.setup();
        paintCanvas.addMouseListener(mouseListener);
    }
}

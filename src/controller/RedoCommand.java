package controller;

import controller.interfaces.ICommand;

public class RedoCommand implements ICommand {
    @Override
    public void run() {
        CommandHistory.redo();
        //System.out.println("redo");
    }
}

package Checkers;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MovesLayerController {
    private final Canvas pane;

    public MovesLayerController(int width, int height) {
        this.pane = new Canvas(width, height);
    }

    public void init() {
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            double x = event.getSceneX();
            double y = event.getSceneY();
            System.out.println("X: " + x + ", Y: " + y);
        });
    }

    public Canvas getPane() {
        return pane;
    }
}

package Checkers;

import Checkers.board.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TmpCheckersCanvas extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation("path/to/fxml");
//        Pane pane = loader.load();
        Board board = new Board();
        board.init();
        TmpBoard tmpBoard = new TmpBoard(board);
        primaryStage.setScene(tmpBoard.create());
        primaryStage.show();
    }
}

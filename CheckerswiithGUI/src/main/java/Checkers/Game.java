package Checkers;

import Checkers.board.Board;
import Checkers.figure.Figure;
import Checkers.figure.Pawn;
import Checkers.figure.Queen;
import Checkers.move.MoveExecutor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application {
    private Board board = new Board();
    private GridPane gpane;
    private int oldX = -1;
    private int oldY = -1;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int FIELDS_AMOUNT = 8;
    private static final int TILE_SIZE = 50;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        board = new Board();
        board.init();
            gpane = new GridPane();
            gpane.setPrefSize(WIDTH, HEIGHT);
            gpane.setGridLinesVisible(true);
            displayOnGrid();

            Scene scene = new Scene(gpane);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public void doMove(int x, int y) {
        if (oldX == -1) {
            oldX = x;
            oldY = y;
        } else {
            board.move(oldX, oldY, x, y);
            oldX = -1;
            oldY = -1;
        }
        displayOnGrid();
    }

    private void displayOnGrid() {
        System.out.println("Grid cleam");
        gpane.getChildren().clear();

        final int numCols = 8;
        final int numRows = 8;

        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows; row++) {
                AnchorPane box = new AnchorPane();
                final int x = col;
                final int y = row;

                box.setOnMouseClicked(event -> doMove(x, y));

                Rectangle square = new Rectangle(50, 50);
                if ((col + row) % 2 == 0) square.setFill(Color.GHOSTWHITE);
                else square.setFill(Color.DARKSLATEGRAY);
                if (row == oldY && col == oldX) {
                    square.setFill(Color.RED);
                }
                box.getChildren().addAll(square);

                Figure figure = board.getFigure(col, row);
                if (figure instanceof Pawn || figure instanceof Queen) {
                    if (figure.getColor() == Figure.Color.WHITE) {
                        Circle circle = whitePiece();
                        box.getChildren().add(circle);
                    } else {
                        Circle circle = blackPiece();
                        box.getChildren().add(circle);
                    }
                }

                gpane.add(box, col, row);
            }
        }
    }

    private Circle blackPiece() {
        return createPiece(Color.DARKGRAY);
    }

    private Circle whitePiece() {
        return createPiece(Color.GHOSTWHITE);
    }

    private Circle createPiece(Color color) {
        Circle circle = new Circle(15);
        circle.setCenterX(25);
        circle.setCenterY(25);
        circle.setFill(color);
        return circle;
    }

}
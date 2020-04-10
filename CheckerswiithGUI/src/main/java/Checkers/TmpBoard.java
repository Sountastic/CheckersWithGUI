package Checkers;

import Checkers.board.Board;
import Checkers.figure.Figure;
import Checkers.figure.Pawn;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TmpBoard {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int FIELDS_AMOUNT = 8;
    private static final int TILE_SIZE = 50;
    private final Board board;
    private final MovesLayerController movesLayerController = new MovesLayerController(WIDTH, HEIGHT);

    Canvas boardCanvas = new Canvas(WIDTH, HEIGHT);
    Canvas pieceCanvas = new Canvas(WIDTH, HEIGHT);

    private final class Tile {
        final int X;
        final int Y;

        public Tile(int x, int y) {
            X = x;
            Y = y;
        }
    }

    public TmpBoard(Board board) {
        this.board = board;
    }

    public Scene create() {
        fillBoardCanvas();
        fillPieceCanvas();

        Pane root = new Pane();
        root.getChildren().add(boardCanvas);
        root.getChildren().add(pieceCanvas);
        pieceCanvas.toFront();

        movesLayerController.init();
        Canvas movesLayer = movesLayerController.getPane();
        root.getChildren().add(movesLayer);
        movesLayer.toFront();
        System.out.println("Hello");

        return new Scene(root);
    }

    private void fillBoardCanvas() {
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        for (int x = 0; x < FIELDS_AMOUNT; x++) {
            for (int y = 0; y < FIELDS_AMOUNT; y++) {
                if ((x + y) % 2 == 0) gc.setFill(Color.GHOSTWHITE);
                else gc.setFill(Color.DARKSLATEGRAY);
                gc.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void fillPieceCanvas() {
        GraphicsContext gc = pieceCanvas.getGraphicsContext2D();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < board.rows.size(); j++) {
                Figure figure = board.getFigure(i, j);
                if (figure instanceof Pawn) {
                    if (figure.getColor() == Figure.Color.WHITE) {
                        gc.setFill(Color.WHITE);
                    } else {
                        gc.setFill(Color.BLACK);
                    }
                    Tile tile = getTile(i, j);
                    gc.fillOval(tile.X + 5, tile.Y + 5, TILE_SIZE - 10, TILE_SIZE - 10);
                }
            }
        }
    }


//        GraphicsContext gc = pieceCanvas.getGraphicsContext2D();
//        gc.setFill(Color.BLACK);
//        for (int x = 0; x < FIELDS_AMOUNT; x++) {
//            for (int y = 0; y < 3; y++) {
//                if ((x + y) % 2 != 0) {
//                    Tile tile = getTile(x, y);
//                    gc.fillOval(tile.X, tile.Y, TILE_SIZE, TILE_SIZE * 0.9);
//                }
//            }
//        }
//
//        gc.setFill(Color.WHITE);
//        gc.setStroke(Color.BLACK);
//        for (int x = 0; x < FIELDS_AMOUNT; x++) {
//            for (int y = 5; y < 8; y++) {
//                if ((x + y) % 2 != 0) {
//                    Tile tile = getTile(x, y);
//                    gc.fillOval(tile.X, tile.Y, TILE_SIZE, TILE_SIZE * 0.9);
//                }
//            }
//        }

    private Tile getTile(int col, int row) {
        if (row > FIELDS_AMOUNT || col > FIELDS_AMOUNT)
            throw new IndexOutOfBoundsException("Wrong index");
        return new Tile(col * TILE_SIZE, row * TILE_SIZE);
    }
}

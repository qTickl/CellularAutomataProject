package com.qtickl.javafx_1;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DisplayCA {
    private CellularAutomata CA;
    private Group root;
    private int iterations;
    private int squareWidth;

    public DisplayCA(CellularAutomata ca) {
        this.CA = ca;
        this.root = new Group();
    }

    /**
     * Runs a one-dimensional cellular automata model for the specified number of iterations
     *
     * @param iterations
     */
    public void runAll(int iterations) {
        this.iterations = iterations;
        calculateSquareWidth(iterations);

        int screenWidth = CA.initRow.length * squareWidth + 60;
        int screenHeight = squareWidth * iterations + 60;
        Stage stage = new Stage();
        Scene scene = new Scene(root, screenWidth, screenHeight, Color.LIGHTGRAY);
        stage.setResizable(true);
        stage.setTitle("Run");

        byte[][] result = CA.iterate(iterations);

        for (int i = 0; i < result.length; i++) {
            createRow(result[i], i * squareWidth + 30);
        }

        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();

    }

    private void createRow(byte[] row, double y) {
        for (int i = 0; i < row.length; i++) {
            Rectangle r = new Rectangle();

            r.setHeight(squareWidth);
            r.setWidth(squareWidth);
            r.setX(i * squareWidth + 30);
            r.setY(y);
            r.setStroke(Color.BLACK);
            r.setStrokeWidth(0.5);
            if (row[i] == 1) {
                r.setFill(Color.DARKGRAY);
            } else {
                r.setFill(Color.WHITE);
            }
            root.getChildren().add(r);
        }
    }

    private void calculateSquareWidth(int iterations) {
        if (iterations >= 20) {
            squareWidth = (int) ((750.0 / iterations));
        } else {
            squareWidth = 20;
        }
    }


}

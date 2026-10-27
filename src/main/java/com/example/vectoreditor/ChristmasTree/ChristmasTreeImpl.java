package com.example.vectoreditor.ChristmasTree;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class ChristmasTreeImpl implements ChristmasTree {

    @Override
    public void draw(Pane pane) {
        double cx = 400;   // центр ёлки по X
        double top = 60;   // верхушка

        // ствол
        Rectangle trunk = new Rectangle(cx - 25, 470, 50, 60);
        trunk.setFill(Color.SADDLEBROWN);
        pane.getChildren().add(trunk);

        // три яруса кроны
        pane.getChildren().add(branch(cx, top,      240, 160, 130, Color.DARKGREEN));
        pane.getChildren().add(branch(cx, top + 120, 300, 200, 170, Color.DARKGREEN));
        pane.getChildren().add(branch(cx, top + 250, 360, 240, 210, Color.DARKGREEN));
    }

    private Polygon branch(double cx, double topY, double width, double height,
                           double baseY, Color color) {
        Polygon p = new Polygon(
                cx, topY,
                cx - width / 2, topY + height,
                cx + width / 2, topY + height
        );
        p.setFill(color);
        return p;
    }

    @Override public String decorate() { return "Ёлка"; }

}

package com.example.vectoreditor.ChristmasTree;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Star extends TreeDecorator {

    public Star(ChristmasTree tree) { super(tree); }

    @Override
    public void draw(Pane pane) {
        super.draw(pane);

        double cx = 400, cy = 40, r = 35;
        Polygon star = new Polygon();
        for (int i = 0; i < 10; i++) {
            double angle = Math.PI / 5 * i - Math.PI / 2;
            double radius = (i % 2 == 0) ? r : r / 2.5;
            star.getPoints().addAll(
                    cx + radius * Math.cos(angle),
                    cy + radius * Math.sin(angle)
            );
        }
        star.setFill(Color.GOLD);
        star.setStroke(Color.DARKGOLDENROD);
        star.setStrokeWidth(2);
        pane.getChildren().add(star);
    }

    @Override public String decorate() { return tree.decorate() + " + Звезда"; }

}

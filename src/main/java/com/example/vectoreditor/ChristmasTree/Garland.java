package com.example.vectoreditor.ChristmasTree;

import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;

public class Garland extends TreeDecorator {
    private static final Color[] PALETTE = {
            Color.RED, Color.YELLOW, Color.CYAN, Color.MAGENTA,
            Color.LIME, Color.ORANGE, Color.WHITE, Color.BLUE
    };

    public Garland(ChristmasTree tree) { super(tree); }

    @Override
    public void draw(Pane pane) {
        super.draw(pane);
        Random rnd = new Random();

        // координаты для 10 шариков вдоль «ёлочных ярусов»
        double[][] positions = {
                {400, 110}, {360, 160}, {440, 160},
                {320, 230}, {400, 230}, {480, 230},
                {280, 320}, {400, 320}, {520, 320},
                {240, 420}, {400, 420}, {560, 420}
        };

        for (double[] pos : positions) {
            Color color = PALETTE[rnd.nextInt(PALETTE.length)];
            Circle bulb = new Circle(pos[0], pos[1], 9);
            bulb.setFill(color);
            pane.getChildren().add(bulb);

            // анимация мигания
            FadeTransition ft = new FadeTransition(Duration.seconds(1.2), bulb);
            ft.setFromValue(1.0);
            ft.setToValue(0.2);
            ft.setCycleCount(FadeTransition.INDEFINITE);
            ft.setAutoReverse(true);
            ft.play();
        }
    }

    @Override public String decorate() { return tree.decorate() + " + Гирлянда"; }

}
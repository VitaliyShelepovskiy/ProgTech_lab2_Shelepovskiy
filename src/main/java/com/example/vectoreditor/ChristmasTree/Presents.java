package com.example.vectoreditor.ChristmasTree;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Presents extends TreeDecorator {
    private static final Color[] BOX_COLORS = {
            Color.RED, Color.BLUE, Color.MAGENTA, Color.YELLOW,
            Color.DARKVIOLET, Color.CRIMSON
    };

    public Presents(ChristmasTree tree) { super(tree); }

    @Override
    public void draw(Pane pane) {
        super.draw(pane);

        // координаты, размеры, индексы цветов
        double[][] boxes = {
                {170, 480, 70, 70, 0},  // x, y, w, h, цвет
                {240, 500, 60, 50, 3},
                {300, 480, 120, 70, 4},
                {440, 490, 80, 60, 1},
                {530, 480, 80, 70, 2},
                {620, 500, 50, 50, 1}
        };

        for (double[] b : boxes) {
            double x = b[0], y = b[1], w = b[2], h = b[3];
            Color color = BOX_COLORS[(int) b[4]];

            Rectangle box = new Rectangle(x, y, w, h);
            box.setFill(color);
            box.setStroke(Color.BLACK);
            box.setStrokeWidth(2);
            pane.getChildren().add(box);

            // лента — вертикальная полоса по центру
            Rectangle ribbonV = new Rectangle(x + w / 2 - 4, y, 8, h);
            ribbonV.setFill(Color.YELLOW);
            pane.getChildren().add(ribbonV);

            // лента — горизонтальная полоса по центру
            Rectangle ribbonH = new Rectangle(x, y + h / 2 - 4, w, 8);
            ribbonH.setFill(Color.YELLOW);
            pane.getChildren().add(ribbonH);
        }
    }

    @Override public String decorate() { return tree.decorate() + " + Подарки"; }

}

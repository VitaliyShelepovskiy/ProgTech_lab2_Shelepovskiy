package com.example.vectoreditor.ChristmasTree;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ornaments extends TreeDecorator {
    private static final Color[] COLORS = {
            Color.RED, Color.BLUE, Color.CYAN, Color.MAGENTA,
            Color.YELLOW, Color.LIME, Color.ORANGE, Color.WHITE
    };

    public Ornaments(ChristmasTree tree) { super(tree); }

    @Override
    public void draw(Pane pane) {
        super.draw(pane);
        Random rnd = new Random();

        // три яруса кроны — треугольники
        double[][] tiers = {
                {400, 60, 320, 220},   // cx, topY, baseLeftX, baseY
                {400, 180, 260, 340},
                {400, 310, 220, 470}
        };

        for (int i = 0; i < 20; i++) {
            double[] t = tiers[rnd.nextInt(tiers.length)];
            double cx = t[0], topY = t[1];
            double baseLeftX = t[2], baseY = t[3];
            double baseRightX = 2 * cx - baseLeftX;   // симметрично
            double height = baseY - topY;

            double x, y;
            // rejection sampling: точка внутри треугольника
            do {
                x = cx + (rnd.nextDouble() * 2 - 1) * (cx - baseLeftX);
                y = topY + rnd.nextDouble() * height;
            } while (!insideTriangle(x, y,
                    cx, topY,
                    baseLeftX, baseY,
                    baseRightX, baseY));

            Circle ball = new Circle(x, y, 7);
            ball.setFill(COLORS[rnd.nextInt(COLORS.length)]);
            ball.setStroke(Color.BLACK);
            ball.setStrokeWidth(1);
            pane.getChildren().add(ball);
        }
    }

    private boolean insideTriangle(double px, double py,
                                   double x1, double y1,
                                   double x2, double y2,
                                   double x3, double y3) {
        double d1 = sign(px, py, x1, y1, x2, y2);
        double d2 = sign(px, py, x2, y2, x3, y3);
        double d3 = sign(px, py, x3, y3, x1, y1);
        boolean hasNeg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        boolean hasPos = (d1 > 0) || (d2 > 0) || (d3 > 0);
        return !(hasNeg && hasPos);
    }

    private double sign(double px, double py,
                        double x1, double y1,
                        double x2, double y2) {
        return (px - x2) * (y1 - y2) - (x1 - x2) * (py - y2);
    }

    @Override public String decorate() { return tree.decorate() + " + Игрушки"; }

}

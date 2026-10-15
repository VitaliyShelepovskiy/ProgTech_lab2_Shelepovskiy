package com.example.prog_tech_lab2.decorator;

import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.paint.Color;

public class Girland extends TreeDecorator{
    public Circle[] circle = new Circle[9];
    public Girland(ChristmasTree tree) {
        super(tree);
    }
    private void draw(Pane paneLight)
    {
        super.draw(paneLight);
        drawWithGirland(paneLight);
    }
    private void drawWithGirland(Pane paneLight)
    {
        FadeTransition[] ft = new FadeTransition[9];

    }
}

package com.example.prog_tech_lab2.decorator;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class Star extends TreeDecorator {
    public Star(ChristmasTree tree)
    {
        super(tree);
    }
    public void draw(Pane paneStar)
    {
        super.draw(paneStar);
        drawStar(paneStar);
    }
    private void drawStar(Pane paneStar)
    {
        Path star = new Path();
        star.getElements().addAll(new MoveTo(239, 49),
                new LineTo(217, 102),
                new LineTo(239, 91),
                new LineTo(262, 102),
                new ClosePath(),
                new MoveTo(207, 68),
                new LineTo(270, 68),
                new LineTo(239,91),
                new ClosePath());
        star.setFill(Color.YELLOW);
        star.setFillRule(FillRule.EVEN_ODD);
        paneStar.getChildren().addAll(star);
    }
}

package com.example.prog_tech_lab2.decorator;

import javafx.scene.layout.Pane;

public abstract class TreeDecorator {
    private ChristmasTree tree;
    public TreeDecorator(ChristmasTree tree)
    {
        this.tree = tree;
    }
    public void draw(Pane pane)
    {
        tree.draw(pane);
    }
}

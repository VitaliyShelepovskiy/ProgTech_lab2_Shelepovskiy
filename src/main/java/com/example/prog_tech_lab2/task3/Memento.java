package com.example.prog_tech_lab2.task3;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.paint.Color;

public class Memento {
    private final Shape shape;
    private final double width;
    private final Color FillColor;
    private final double x, y;

    public Memento(Shape state) {
        this.shape = state;
        this.width = state.getStrokeWidth();
        this.FillColor = state.getFillColor();
        this.x = state.getX();
        this.y = state.getY();
    }

    public Shape initState() {
        shape.setStrokeWidth(2);
        shape.setFillColor(Color.RED);

        return shape;
    }

    public Shape getState() { return shape; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public Color getFillColor() { return FillColor; }
}
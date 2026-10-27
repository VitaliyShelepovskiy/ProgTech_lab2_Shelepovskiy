package com.example.prog_tech_lab2.task1;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Shape {
    private final double rx, ry;

    public Ellipse(Color strokeColor, double x, double y, double rx, double ry) {
        super(strokeColor, x, y);
        this.rx = rx;
        this.ry = ry;
    }

    @Override public double area() { return Math.PI * rx * ry; }

    @Override
    public void draw(GraphicsContext gc) {
        if (getFillColor() != Color.TRANSPARENT) {
            gc.setFill(getFillColor());
            gc.fillOval(x - rx, y - ry, rx * 2, ry * 2);
        }
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());
        gc.strokeOval(x - rx, y - ry, rx * 2, ry * 2);
    }

    @Override
    public boolean contains(double mx, double my) {
        double dx = (mx - x) / rx, dy = (my - y) / ry;
        return dx * dx + dy * dy <= 1;
    }

    @Override
    public Shape copy() {
        Ellipse e = new Ellipse(getStrokeColor(), getX(), getY(), rx, ry);
        e.setFillColor(getFillColor());
        e.setStrokeWidth(getStrokeWidth());
        return e;
    }

    @Override public String discriptor() { return "Ellipse"; }
}
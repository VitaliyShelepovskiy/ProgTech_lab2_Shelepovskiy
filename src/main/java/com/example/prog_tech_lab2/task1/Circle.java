package com.example.prog_tech_lab2.task1;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    private final double radius;

    public Circle(Color strokeColor, double x, double y, double radius) {
        super(strokeColor, x, y);
        this.radius = radius;
    }

    @Override public double area() { return Math.PI * radius * radius; }

    @Override
    public void draw(GraphicsContext gc) {
        if (getFillColor() != Color.TRANSPARENT) {
            gc.setFill(getFillColor());
            gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());
        gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public boolean contains(double mx, double my) {
        double dx = mx - x, dy = my - y;
        return dx * dx + dy * dy <= radius * radius;
    }

    @Override
    public Shape copy() {
        Circle c = new Circle(getStrokeColor(), getX(), getY(), radius);
        c.setFillColor(getFillColor());
        c.setStrokeWidth(getStrokeWidth());
        return c;
    }

    @Override public String discriptor() { return "Circle"; }
}
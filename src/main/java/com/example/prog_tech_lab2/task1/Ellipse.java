package com.example.prog_tech_lab2.task1;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Shape {
    private final double rx, ry;

    public Ellipse(Color fillColor,Color strokeColor, double x, double y, double rx, double ry) {
        super(fillColor, strokeColor,x, y);
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public double area() { return Math.PI * rx * ry; }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());
        gc.fillOval(x - rx, y - ry, rx * 2, ry * 2);
        gc.strokeOval(x - rx, y - ry, rx * 2, ry * 2);
    }

    @Override
    public String discriptor() { return "Ellipse"; }
}

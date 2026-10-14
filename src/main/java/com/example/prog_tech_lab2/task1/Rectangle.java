package com.example.prog_tech_lab2.task1;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    protected double length;
    protected double width;

    public Rectangle(Color fillColor, Color strokeColor, double x, double y, double length, double width) {
        super(fillColor,strokeColor, x, y);
        this.length = length;
        this.width = width;
    }

    @Override public double area() { return length * width; }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());
        gc.fillRect(x - length / 2, y - width / 2, length, width);
        gc.strokeRect(x - length / 2, y - width / 2, length, width);
    }

    @Override
    public boolean contains(double mx, double my) {
        return mx >= x - length / 2 && mx <= x + length / 2
                && my >= y - width / 2 && my <= y + width / 2;
    }

    @Override
    public String discriptor() { return "Rectangle"; }
}
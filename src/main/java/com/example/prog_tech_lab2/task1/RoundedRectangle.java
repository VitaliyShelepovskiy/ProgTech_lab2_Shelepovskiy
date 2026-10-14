package com.example.prog_tech_lab2.task1;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RoundedRectangle extends Shape {
    private final double length, width, arc;

    public RoundedRectangle(Color fillColor,Color strokeColor, double x, double y, double length, double width, double arc) {
        super(fillColor,strokeColor, x, y);
        this.length = length;
        this.width = width;
        this.arc = arc;
    }

    @Override
    public double area() { return length * width; }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());
        gc.fillRoundRect(x - length / 2, y - width / 2, length, width, arc, arc);
        gc.strokeRoundRect(x - length / 2, y - width / 2, length, width, arc, arc);
    }

    @Override
    public String discriptor() { return "RoundedRectangle"; }
}
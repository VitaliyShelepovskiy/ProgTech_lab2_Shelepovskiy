package com.example.prog_tech_lab2.task1;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RoundedRectangle extends Shape {
    private final double length, width, arc;

    public RoundedRectangle(Color strokeColor, double x, double y,
                            double length, double width, double arc) {
        super(strokeColor, x, y);
        this.length = length;
        this.width = width;
        this.arc = arc;
    }

    @Override public double area() { return length * width; }

    @Override
    public void draw(GraphicsContext gc) {
        if (getFillColor() != Color.TRANSPARENT) {
            gc.setFill(getFillColor());
            gc.fillRoundRect(x - length / 2, y - width / 2, length, width, arc, arc);
        }
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());
        gc.strokeRoundRect(x - length / 2, y - width / 2, length, width, arc, arc);
    }

    @Override
    public boolean contains(double mx, double my) {
        return mx >= x - length / 2 && mx <= x + length / 2
                && my >= y - width / 2 && my <= y + width / 2;
    }

    @Override
    public Shape copy() {
        RoundedRectangle r = new RoundedRectangle(getStrokeColor(), getX(), getY(), length, width, arc);
        r.setFillColor(getFillColor());
        r.setStrokeWidth(getStrokeWidth());
        return r;
    }

    @Override public String discriptor() { return "RoundedRectangle"; }
}
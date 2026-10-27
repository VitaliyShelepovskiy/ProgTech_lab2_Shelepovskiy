package com.example.prog_tech_lab2.task2;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AndElement extends Shape {
    private double scale = 1.0;

    public AndElement(Color strokeColor) { super(strokeColor, 400, 250); }
    public void setScale(double s) { this.scale = s; }

    @Override public double area() { return 0; }

    @Override
    public void draw(GraphicsContext gc) {
        double w = 120 * scale, h = 80 * scale;

        gc.beginPath();
        gc.moveTo(x - w / 2, y - h / 2);
        gc.lineTo(x, y - h / 2);
        gc.arc(x, y, h / 2, h / 2, -90, 180);
        gc.lineTo(x - w / 2, y + h / 2);
        gc.closePath();

        if (getFillColor() != Color.TRANSPARENT) {
            gc.setFill(getFillColor());
            gc.fill();
        }
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());
        gc.stroke();

        gc.strokeLine(x - w / 2 - 20, y - h / 4, x - w / 2, y - h / 4);
        gc.strokeLine(x - w / 2 - 20, y + h / 4, x - w / 2, y + h / 4);
        gc.strokeLine(x + h / 2, y, x + h / 2 + 20, y);
        gc.strokeText("AND", x - 20, y + 5);
    }

    @Override
    public boolean contains(double mx, double my) {
        double w = 120 * scale, h = 80 * scale;
        return mx >= x - w / 2 && mx <= x + w / 2 + h / 2
                && my >= y - h / 2 && my <= y + h / 2;
    }

    @Override
    public Shape copy() {
        AndElement o = new AndElement(getStrokeColor());
        o.setPosition(getX(), getY());
        o.setScale(scale);
        o.setFillColor(getFillColor());
        o.setStrokeWidth(getStrokeWidth());
        return o;
    }

    @Override public String discriptor() { return "AND"; }
}
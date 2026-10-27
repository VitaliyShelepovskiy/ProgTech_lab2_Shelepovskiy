package com.example.prog_tech_lab2.task2;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NorElement extends Shape {
    private double scale = 1.0;

    public NorElement(Color strokeColor) { super(strokeColor, 400, 250); }
    public void setScale(double s) { this.scale = s; }

    @Override public double area() { return 0; }

    @Override
    public void draw(GraphicsContext gc) {
        double w = 120 * scale, h = 80 * scale;

        gc.beginPath();
        gc.moveTo(x - w / 2, y - h / 2);
        gc.quadraticCurveTo(x - w / 4, y, x - w / 2, y + h / 2);
        gc.quadraticCurveTo(x, y + h / 2, x + w / 2, y);
        gc.quadraticCurveTo(x, y - h / 2, x - w / 2, y - h / 2);
        gc.closePath();

        if (getFillColor() != Color.TRANSPARENT) {
            gc.setFill(getFillColor());
            gc.fill();
        }
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());
        gc.stroke();

        // кружок инверсии — заливаем фоном, чтобы читался как «дырка»
        gc.setFill(Color.WHITE);
        gc.fillOval(x + w / 2, y - 6, 12, 12);
        gc.setStroke(getStrokeColor());
        gc.strokeOval(x + w / 2, y - 6, 12, 12);

        gc.strokeLine(x - w / 2 - 20, y - h / 4, x - w / 2 + 5, y - h / 4);
        gc.strokeLine(x - w / 2 - 20, y + h / 4, x - w / 2 + 5, y + h / 4);
        gc.strokeLine(x + w / 2 + 12, y, x + w / 2 + 30, y);
        gc.strokeText("NOR", x - 8, y + 5);
    }

    @Override
    public boolean contains(double mx, double my) {
        double w = 120 * scale, h = 80 * scale;
        return mx >= x - w / 2 && mx <= x + w / 2
                && my >= y - h / 2 && my <= y + h / 2;
    }

    @Override
    public Shape copy() {
        NorElement o = new NorElement(getStrokeColor());
        o.setPosition(getX(), getY());
        o.setScale(scale);
        o.setFillColor(getFillColor());
        o.setStrokeWidth(getStrokeWidth());
        return o;
    }

    @Override public String discriptor() { return "NOR"; }
}

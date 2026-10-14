package com.example.prog_tech_lab2.task2;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NotElement extends Shape {
    private double scale = 1.0;

    public NotElement(Color fillColor, Color strokeColor) { super(fillColor, strokeColor,400, 250); }


    @Override public double area() { return 0; }

    @Override
    public void draw(GraphicsContext gc) {
        double w = 100 * scale, h = 80 * scale;
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(3);
        gc.beginPath();
        gc.moveTo(x - w / 2, y - h / 2);
        gc.lineTo(x - w / 2, y + h / 2);
        gc.lineTo(x + w / 2, y);
        gc.closePath();
        gc.setFill(getFillColor());
        gc.fill();
        gc.stroke();
        gc.strokeOval(x + w / 2, y - 6, 12, 12);
        gc.strokeLine(x - w / 2 - 20, y, x - w / 2, y);
        gc.strokeLine(x + w / 2 + 12, y, x + w / 2 + 30, y);
        gc.strokeText("NOT", x - 20, y + 5);
    }

    @Override public String discriptor() { return "NOT"; }
}
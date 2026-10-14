package com.example.prog_tech_lab2.task2;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NandElement extends Shape {
    private double scale = 1.0;

    public NandElement(Color fillColor, Color strokeColor) { super(fillColor, strokeColor, 400, 250); }
    public void setScale(double s) { this.scale = s; }

    @Override public double area() { return 0; }

    @Override
    public void draw(GraphicsContext gc) {
        double w = 120 * scale, h = 80 * scale;
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(3);
        gc.beginPath();
        gc.moveTo(x - w / 2, y - h / 2);
        gc.lineTo(x, y - h / 2);
        gc.arc(x, y, h / 2, h / 2, -90, 180);
        gc.lineTo(x - w / 2, y + h / 2);
        gc.closePath();
        gc.setFill(getFillColor());
        gc.fill();
        gc.stroke();
        gc.strokeOval(x + h / 2, y - 6, 12, 12);
        gc.strokeLine(x - w / 2 - 20, y - h / 4, x - w / 2, y - h / 4);
        gc.strokeLine(x - w / 2 - 20, y + h / 4, x - w / 2, y + h / 4);
        gc.strokeLine(x + h / 2 + 12, y, x + h / 2 + 30, y);
        gc.strokeText("NAND", x - 25, y + 5);
    }

    @Override public String discriptor() { return "NAND"; }
}
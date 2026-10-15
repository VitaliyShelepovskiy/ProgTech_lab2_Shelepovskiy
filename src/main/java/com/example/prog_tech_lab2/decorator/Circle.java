package com.example.prog_tech_lab2.decorator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.example.prog_tech_lab2.Shape;
public class Circle extends Shape {
    private final double radius;

    public Circle(Color fillColor, Color strokeColor, double x, double y, double radius) {
        super(fillColor,strokeColor, x, y);
        this.radius = radius;
    }

    @Override public double area() { return Math.PI * radius * radius; }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.setLineWidth(getStrokeWidth());
        gc.setStroke(getStrokeColor());
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        gc.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public boolean contains(double mx, double my) {
        double dx = mx - x, dy = my - y;
        return dx * dx + dy * dy <= radius * radius;
    }

    @Override public String discriptor() { return "Circle"; }
}

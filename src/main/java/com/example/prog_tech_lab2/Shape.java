package com.example.prog_tech_lab2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {

    private Color FillColor;
    private Color StrokeColor;
    protected double x, y;
    protected double strokeWidth = 6;

    public Shape(Color fillColor,Color strokeColor, double x, double y) {
        this.FillColor = fillColor;
        this.StrokeColor = strokeColor;
        this.x = x;
        this.y = y;
        System.out.println("Shape constructor called");
    }


    public abstract double area();
    public abstract void draw(GraphicsContext gc);
    public abstract String discriptor();


    public Color getFillColor() { return FillColor; }
    public void setFillColor(Color fillColor) { this.FillColor = fillColor; }
    public Color getStrokeColor() { return StrokeColor; }
    public void setStrokeColor(Color strokeColor) { this.StrokeColor = strokeColor; }
    public double getX() { return x; }
    public double getY() { return y; }
    public void setPosition(double x, double y) { this.x = x; this.y = y; }

    public double getStrokeWidth() { return strokeWidth; }
    public void setStrokeWidth(double w) { this.strokeWidth = w; }


    public boolean contains(double mx, double my) {
        return false; // переопределяется в подклассах
    }

    @Override
    public String toString() {
        return discriptor() + " Fillcolor=" + FillColor + " StrokeColor=" + StrokeColor+ " area=" + String.format("%.2f", area());
    }
}

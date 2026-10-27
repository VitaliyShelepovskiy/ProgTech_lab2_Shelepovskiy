package com.example.prog_tech_lab2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    private Color fillColor = Color.TRANSPARENT;   // цвет заливки
    private Color strokeColor = Color.BLACK;       // цвет контура
    protected double strokeWidth = 1;
    protected double x, y;                          // центр фигуры

    public Shape(Color strokeColor, double x, double y) {
        this.strokeColor = strokeColor;
        this.x = x;
        this.y = y;
    }

    // абстрактные методы
    public abstract double area();
    public abstract void draw(GraphicsContext gc);
    public abstract String discriptor();
    public abstract Shape copy();

    // общие геттеры/сеттеры
    public Color getFillColor() { return fillColor; }
    public void setFillColor(Color c) { this.fillColor = c; }

    public Color getStrokeColor() { return strokeColor; }
    public void setStrokeColor(Color c) { this.strokeColor = c; }

    // для совместимости с Task 3, где использовался getColor()/setColor()
    public Color getColor() { return strokeColor; }
    public void setColor(Color c) { this.strokeColor = c; }

    public double getStrokeWidth() { return strokeWidth; }
    public void setStrokeWidth(double w) { this.strokeWidth = w; }

    public double getX() { return x; }
    public double getY() { return y; }
    public void relocate(double x, double y) { this.x = x; this.y = y; }
    public void setPosition(double x, double y) { this.x = x; this.y = y; }

    public boolean contains(double mx, double my) { return false; }

    @Override
    public String toString() {
        return discriptor() + " stroke=" + strokeColor + " fill=" + fillColor;
    }
}
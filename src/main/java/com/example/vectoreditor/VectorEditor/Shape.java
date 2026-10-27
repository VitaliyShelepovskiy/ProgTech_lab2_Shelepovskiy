package com.example.vectoreditor.VectorEditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Color fillColor = Color.TRANSPARENT;
    protected Color strokeColor = Color.BLACK;
    protected double strokeWidth = 2;

    public abstract void draw(GraphicsContext gc);
    public abstract boolean contains(double mx, double my);
    public abstract void relocate(double dx, double dy);
    public abstract double getX();
    public abstract double getY();
    public abstract String descriptor();
    public abstract Shape copy();

    public Color getFillColor() { return fillColor; }
    public void setFillColor(Color c) { this.fillColor = c; }

    public Color getStrokeColor() { return strokeColor; }
    public void setStrokeColor(Color c) { this.strokeColor = c; }

    public double getStrokeWidth() { return strokeWidth; }
    public void setStrokeWidth(double w) { this.strokeWidth = w; }
}
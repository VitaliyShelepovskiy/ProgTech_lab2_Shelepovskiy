package com.example.vectoreditor.VectorEditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class ShapeDecorator extends Shape {
    protected Shape wrapped;

    public ShapeDecorator(Shape wrapped) { this.wrapped = wrapped; }

    public Shape getWrapped() { return wrapped; }

    @Override public void draw(GraphicsContext gc) { wrapped.draw(gc); }
    @Override public boolean contains(double mx, double my) { return wrapped.contains(mx, my); }
    @Override public void relocate(double dx, double dy) { wrapped.relocate(dx, dy); }
    @Override public double getX() { return wrapped.getX(); }
    @Override public double getY() { return wrapped.getY(); }
    @Override public String descriptor() { return wrapped.descriptor(); }
    @Override public Shape copy() { return wrapped.copy(); }

    @Override public Color getFillColor() { return wrapped.getFillColor(); }
    @Override public void setFillColor(Color c) { wrapped.setFillColor(c); }
    @Override public Color getStrokeColor() { return wrapped.getStrokeColor(); }
    @Override public void setStrokeColor(Color c) { wrapped.setStrokeColor(c); }
    @Override public double getStrokeWidth() { return wrapped.getStrokeWidth(); }
    @Override public void setStrokeWidth(double w) { wrapped.setStrokeWidth(w); }
}
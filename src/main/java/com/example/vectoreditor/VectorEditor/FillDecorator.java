package com.example.vectoreditor.VectorEditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FillDecorator extends ShapeDecorator {
    private Color color;

    public FillDecorator(Shape wrapped, Color color) {
        super(wrapped);
        this.color = (color == null || color == Color.TRANSPARENT)
                ? Color.LIGHTBLUE
                : color;
        wrapped.setFillColor(this.color);
    }

    public void setColor(Color c) {
        this.color = c;
        wrapped.setFillColor(c);
    }
    public Color getColor() { return color; }

    @Override public void draw(GraphicsContext gc) { wrapped.draw(gc); }
    @Override public String descriptor() { return wrapped.descriptor() + "+Fill"; }

    @Override
    public Shape copy() {
        return new FillDecorator(wrapped.copy(), color);
    }
}
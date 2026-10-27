package com.example.vectoreditor.VectorEditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BorderDecorator extends ShapeDecorator {
    private Color color;
    private double width;

    public BorderDecorator(Shape wrapped, Color color, double width) {
        super(wrapped);
        this.color = (color == null) ? Color.BLACK : color;
        this.width = Math.max(1, width);       // защита от 0
        wrapped.setStrokeColor(this.color);
        wrapped.setStrokeWidth(this.width);
    }

    public void setColor(Color c) { this.color = c; wrapped.setStrokeColor(c); }
    public void setWidth(double w) {
        this.width = Math.max(1, w);
        wrapped.setStrokeWidth(this.width);
    }
    public Color getColor() { return color; }
    public double getBorderWidth() { return width; }

    @Override public void draw(GraphicsContext gc) { wrapped.draw(gc); }
    @Override public String descriptor() { return wrapped.descriptor() + "+Border"; }

    @Override
    public Shape copy() {
        return new BorderDecorator(wrapped.copy(), color, width);
    }
}

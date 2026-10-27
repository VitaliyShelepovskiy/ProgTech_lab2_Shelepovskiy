package com.example.vectoreditor.VectorEditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ShadowDecorator extends ShapeDecorator {
    private final double offsetX;
    private final double offsetY;
    private final Color shadowColor;

    public ShadowDecorator(Shape wrapped, double offsetX, double offsetY, Color shadowColor) {
        super(wrapped);
        this.offsetX = (Math.abs(offsetX) < 1) ? 6 : offsetX;
        this.offsetY = (Math.abs(offsetY) < 1) ? 6 : offsetY;
        this.shadowColor = (shadowColor == null)
                ? Color.rgb(0, 0, 0, 0.4)
                : shadowColor;
    }

    @Override
    public void draw(GraphicsContext gc) {
        // 1. тень — копия со сдвигом, серым/полупрозрачным цветом, без контура
        Shape shadow = wrapped.copy();
        shadow.relocate(offsetX, offsetY);
        shadow.setFillColor(shadowColor);
        shadow.setStrokeColor(shadowColor);
        shadow.setStrokeWidth(0);
        shadow.draw(gc);

        // 2. сама фигура
        wrapped.draw(gc);
    }

    @Override public String descriptor() { return wrapped.descriptor() + "+Shadow"; }

    @Override
    public Shape copy() {
        return new ShadowDecorator(wrapped.copy(), offsetX, offsetY, shadowColor);
    }
}
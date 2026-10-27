package com.example.vectoreditor.VectorEditor;

import javafx.scene.canvas.GraphicsContext;

public class RasterShape extends Shape {
    public enum Kind { POINT, CIRCLE, SQUARE, RECT }

    private final Kind kind;
    private double x, y;
    private double size;

    public RasterShape(Kind kind, double x, double y, double size) {
        this.kind = kind;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public Kind getKind() { return kind; }

    @Override
    public void draw(GraphicsContext gc) {
        double half = size / 2;
        gc.setFill(fillColor);
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeWidth);

        switch (kind) {
            case POINT:
                gc.fillOval(x - strokeWidth, y - strokeWidth, strokeWidth * 2, strokeWidth * 2);
                break;
            case CIRCLE:
                gc.fillOval(x - half, y - half, size, size);
                gc.strokeOval(x - half, y - half, size, size);
                break;
            case SQUARE:
                gc.fillRect(x - half, y - half, size, size);
                gc.strokeRect(x - half, y - half, size, size);
                break;
            case RECT:
                gc.fillRect(x - half, y - half, size, size / 2);
                gc.strokeRect(x - half, y - half, size, size / 2);
                break;
        }
    }

    @Override
    public boolean contains(double mx, double my) {
        double half = size / 2;
        return mx >= x - half && mx <= x + half && my >= y - half && my <= y + half;
    }

    @Override public void relocate(double dx, double dy) { x += dx; y += dy; }
    @Override public double getX() { return x; }
    @Override public double getY() { return y; }

    @Override public String descriptor() { return "Raster:" + kind; }

    @Override
    public Shape copy() {
        RasterShape r = new RasterShape(kind, x, y, size);
        r.setFillColor(fillColor);
        r.setStrokeColor(strokeColor);
        r.setStrokeWidth(strokeWidth);
        return r;
    }
}
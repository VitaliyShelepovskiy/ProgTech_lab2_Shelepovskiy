package com.example.vectoreditor.VectorEditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Polygon extends Shape {
    private final List<double[]> vertices = new ArrayList<>();

    public Polygon() {}

    public Polygon(List<double[]> verts) {
        for (double[] v : verts) vertices.add(new double[]{v[0], v[1]});
    }

    public void addVertex(double x, double y) { vertices.add(new double[]{x, y}); }

    public List<double[]> getVertices() { return vertices; }
    public int size() { return vertices.size(); }

    @Override
    public double getX() {
        if (vertices.isEmpty()) return 0;
        double s = 0;
        for (double[] v : vertices) s += v[0];
        return s / vertices.size();
    }

    @Override
    public double getY() {
        if (vertices.isEmpty()) return 0;
        double s = 0;
        for (double[] v : vertices) s += v[1];
        return s / vertices.size();
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (vertices.size() < 3) return;

        double[] xs = new double[vertices.size()];
        double[] ys = new double[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            xs[i] = vertices.get(i)[0];
            ys[i] = vertices.get(i)[1];
        }

        if (fillColor != null && fillColor != Color.TRANSPARENT) {
            gc.setFill(fillColor);
            gc.fillPolygon(xs, ys, vertices.size());
        }
        if (strokeColor != null && strokeWidth > 0) {
            gc.setStroke(strokeColor);
            gc.setLineWidth(strokeWidth);
            gc.strokePolygon(xs, ys, vertices.size());
        }
    }

    @Override
    public boolean contains(double mx, double my) {
        double minX = Double.MAX_VALUE, maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE, maxY = -Double.MAX_VALUE;
        for (double[] v : vertices) {
            minX = Math.min(minX, v[0]); maxX = Math.max(maxX, v[0]);
            minY = Math.min(minY, v[1]); maxY = Math.max(maxY, v[1]);
        }
        return mx >= minX && mx <= maxX && my >= minY && my <= maxY;
    }

    @Override
    public void relocate(double dx, double dy) {
        for (double[] v : vertices) { v[0] += dx; v[1] += dy; }
    }

    @Override public String descriptor() { return "Polygon(" + vertices.size() + ")"; }

    @Override
    public Shape copy() {
        Polygon p = new Polygon();
        for (double[] v : vertices) p.addVertex(v[0], v[1]);
        p.setFillColor(fillColor);
        p.setStrokeColor(strokeColor);
        p.setStrokeWidth(strokeWidth);
        return p;
    }
}
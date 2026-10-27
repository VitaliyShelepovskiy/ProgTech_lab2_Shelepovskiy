package com.example.prog_tech_lab2.task4;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class FreeShape extends Shape {
    public enum Kind { PENCIL, LINE, ELLIPSE, RECT }

    private final Kind kind;
    private final List<double[]> points = new ArrayList<>();  // для PENCIL
    private double x2, y2;                                    // конец для LINE/ELLIPSE/RECT

    // для LINE / ELLIPSE / RECT
    public FreeShape(Kind kind, Color strokeColor, Color fillColor,
                     double x1, double y1, double x2, double y2,
                     double strokeWidth) {
        super(strokeColor, x1, y1);
        this.kind = kind;
        this.x2 = x2;
        this.y2 = y2;
        setFillColor(fillColor);
        setStrokeWidth(strokeWidth);
    }

    // для PENCIL
    public FreeShape(Color strokeColor, double x1, double y1, double strokeWidth) {
        super(strokeColor, x1, y1);
        this.kind = Kind.PENCIL;
        setStrokeWidth(strokeWidth);
        points.add(new double[]{x1, y1});
    }

    public Kind getKind() { return kind; }
    public void addPoint(double px, double py) { points.add(new double[]{px, py}); }
    public void setEnd(double x2, double y2) { this.x2 = x2; this.y2 = y2; }

    @Override public double area() { return 0; }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());

        switch (kind) {
            case PENCIL:
                gc.beginPath();
                gc.moveTo(points.get(0)[0], points.get(0)[1]);
                for (int i = 1; i < points.size(); i++) {
                    gc.lineTo(points.get(i)[0], points.get(i)[1]);
                }
                gc.stroke();
                break;

            case LINE:
                gc.strokeLine(x, y, x2, y2);
                break;

            case RECT: {
                double rx = Math.min(x, x2), ry = Math.min(y, y2);
                double rw = Math.abs(x2 - x), rh = Math.abs(y2 - y);
                if (getFillColor() != Color.TRANSPARENT) {
                    gc.setFill(getFillColor());
                    gc.fillRect(rx, ry, rw, rh);
                }
                gc.strokeRect(rx, ry, rw, rh);
                break;
            }

            case ELLIPSE: {
                double rx = Math.min(x, x2), ry = Math.min(y, y2);
                double rw = Math.abs(x2 - x), rh = Math.abs(y2 - y);
                if (getFillColor() != Color.TRANSPARENT) {
                    gc.setFill(getFillColor());
                    gc.fillOval(rx, ry, rw, rh);
                }
                gc.strokeOval(rx, ry, rw, rh);
                break;
            }
        }
    }

    @Override
    public boolean contains(double mx, double my) {
        double minX = x, maxX = x, minY = y, maxY = y;
        if (kind == Kind.PENCIL) {
            for (double[] p : points) {
                minX = Math.min(minX, p[0]); maxX = Math.max(maxX, p[0]);
                minY = Math.min(minY, p[1]); maxY = Math.max(maxY, p[1]);
            }
        } else {
            minX = Math.min(x, x2); maxX = Math.max(x, x2);
            minY = Math.min(y, y2); maxY = Math.max(y, y2);
        }
        return mx >= minX && mx <= maxX && my >= minY && my <= maxY;
    }

    @Override
    public void relocate(double nx, double ny) {
        double dx = nx - x, dy = ny - y;
        x = nx; y = ny;
        if (kind == Kind.PENCIL) {
            for (double[] p : points) { p[0] += dx; p[1] += dy; }
        } else {
            x2 += dx; y2 += dy;
        }
    }

    @Override
    public Shape copy() {
        FreeShape c;
        if (kind == Kind.PENCIL) {
            c = new FreeShape(getStrokeColor(), x, y, getStrokeWidth());
            c.points.clear();
            for (double[] p : points) c.points.add(new double[]{p[0], p[1]});
        } else {
            c = new FreeShape(kind, getStrokeColor(), getFillColor(),
                    x, y, x2, y2, getStrokeWidth());
        }
        return c;
    }

    @Override public String discriptor() { return kind.name(); }
}
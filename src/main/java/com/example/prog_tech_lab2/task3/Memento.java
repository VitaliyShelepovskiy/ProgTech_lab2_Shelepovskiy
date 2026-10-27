package com.example.prog_tech_lab2.task3;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    // ─── вариант для Task 3: одна фигура ───
    private Shape shape;
    private double width;
    private Color strokeColor;
    private Color fillColor;
    private double x, y;

    // ─── вариант для Task 4: снимок всего холста ───
    private List<Shape> canvasSnapshot;

    // конструктор для Task 3 (одна фигура)
    public Memento(Shape state) {
        this.shape = state;
        this.width = state.getStrokeWidth();
        this.strokeColor = state.getStrokeColor();
        this.fillColor = state.getFillColor();
        this.x = state.getX();
        this.y = state.getY();
    }

    // конструктор для Task 4 (снимок холста)
    public Memento(List<Shape> canvas) {
        this.canvasSnapshot = new ArrayList<>();
        for (Shape s : canvas) this.canvasSnapshot.add(s.copy());
    }

    // выделение фигуры (Task 3)
    public Shape initState() {
        shape.setStrokeWidth(2);
        shape.setStrokeColor(Color.RED);
        return shape;
    }

    public Shape getState() { return shape; }
    public double getWidth() { return width; }
    public Color getStrokeColor() { return strokeColor; }
    public Color getFillColor() { return fillColor; }
    public double getX() { return x; }
    public double getY() { return y; }
    public Color getColor() { return strokeColor; }  // алиас для Task 3

    public List<Shape> getCanvasSnapshot() { return canvasSnapshot; }
    public boolean isCanvasSnapshot() { return canvasSnapshot != null; }
}
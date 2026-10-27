package com.example.prog_tech_lab2.task1;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.paint.Color;

public class Square extends Rectangle {
    public Square(Color strokeColor, double x, double y, double side) {
        super(strokeColor, x, y, side, side);
    }

    @Override
    public Shape copy() {
        Square s = new Square(getStrokeColor(), getX(), getY(), length);
        s.setFillColor(getFillColor());
        s.setStrokeWidth(getStrokeWidth());
        return s;
    }

    @Override public String discriptor() { return "Square"; }
}
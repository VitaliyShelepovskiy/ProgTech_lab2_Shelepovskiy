package com.example.prog_tech_lab2.task1;

import javafx.scene.paint.Color;

public class Square extends Rectangle {
    public Square(Color fillColor,Color strokeColor, double x, double y, double side) {
        super(fillColor,strokeColor, x, y, side, side);
    }

    @Override
    public String discriptor() { return "Square"; }
}
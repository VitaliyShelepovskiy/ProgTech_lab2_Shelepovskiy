package com.example.prog_tech_lab2.task2;

import javafx.scene.paint.Color;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.paint.Color;

public class ShapeFactory {

    public Shape getShape(int number, Color fillColor, Color strokeColor) {
        switch (number) {
            case 0: return new OrElement(fillColor, strokeColor);
            case 1: return new NorElement(fillColor, strokeColor);
            case 2: return new NandElement(fillColor, strokeColor);
            case 3: return new AndElement(fillColor, strokeColor);
            case 4: return new NotElement(fillColor, strokeColor);
            default: return null;
        }
    }
}

package com.example.prog_tech_lab2.task2;

import com.example.prog_tech_lab2.Shape;
import javafx.scene.paint.Color;

public class ShapeFactory {
    public Shape getShape(int number, Color strokeColor) {
        switch (number) {
            case 0: return new OrElement(strokeColor);
            case 1: return new NorElement(strokeColor);
            case 2: return new NandElement(strokeColor);
            case 3: return new AndElement(strokeColor);
            case 4: return new NotElement(strokeColor);
            default: return null;
        }
    }
}

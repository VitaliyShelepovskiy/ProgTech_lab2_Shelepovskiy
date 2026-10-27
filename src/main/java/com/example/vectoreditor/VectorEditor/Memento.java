package com.example.vectoreditor.VectorEditor;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private final List<Shape> snapshot;

    public Memento(List<Shape> canvas) {
        snapshot = new ArrayList<>();
        for (Shape s : canvas) snapshot.add(s.copy());
    }

    public List<Shape> getSnapshot() { return snapshot; }
}
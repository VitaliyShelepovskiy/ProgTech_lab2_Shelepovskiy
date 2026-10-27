package com.example.vectoreditor.VectorEditor;

import java.util.ArrayDeque;
import java.util.Deque;

public class MemoSelect {
    private final Deque<Memento> stack = new ArrayDeque<>();

    public void push(Memento m) { stack.push(m); }
    public Memento poll() { return stack.poll(); }
    public int size() { return stack.size(); }
    public void clear() { stack.clear(); }
}
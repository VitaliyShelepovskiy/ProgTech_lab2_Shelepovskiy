package com.example.prog_tech_lab2.task3;

import java.util.ArrayDeque;
import java.util.Deque;

public class MemoSelect {
    private final Deque<Memento> mementoList = new ArrayDeque<>();

    public void push(Memento state) { mementoList.push(state); }
    public Memento poll() { return mementoList.poll(); }
    public int size() { return mementoList.size(); }
    public void clear() { mementoList.clear(); }
}

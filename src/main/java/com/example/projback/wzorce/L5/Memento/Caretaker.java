package com.example.projback.wzorce.L5.Memento;

import java.util.Stack;

//###   start L5 Memento -> Caretaker
public class Caretaker<T> {
    private Stack<Memento<T>> history = new Stack<>();

    public void save(Memento<T> memento) {
        history.push(memento);
    }

    public Memento<T> undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}
//###   end L5 Memento -> Caretaker
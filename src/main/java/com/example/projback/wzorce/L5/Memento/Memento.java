package com.example.projback.wzorce.L5.Memento;

//###   start L5 Memento
public class Memento<T> {
    private final T state;

    public Memento(T state) {
        this.state = state;
    }

    public T getState() {
        return state;
    }
}
//###   end L5 Memento
package com.example.projback.wzorce.L5.Iterator;

//###   start L5 Iterator -> Collection
public interface IterableCollection<T> {
    Iterator<T> createIterator();
}
//###   end L5 Iterator -> Collection
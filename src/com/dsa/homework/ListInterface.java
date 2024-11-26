package com.dsa.homework;

public interface ListInterface<T>{

    public void add(T data);
    public T get(int i);
    public int size();
    public boolean isEmpty();
}


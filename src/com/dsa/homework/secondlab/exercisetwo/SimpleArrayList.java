package com.dsa.homework.secondlab.exercisetwo;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayList<T> implements ListInterface<T> {
    private T[] array;
    private int n = 0;
    private int defaultSize = 100;

    public SimpleArrayList() {
        array = (T[]) new Object[defaultSize];
    }

    public SimpleArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T data) {
        if (array.length <= n) {
            enlarge();
        }
        array[n++] = data;
    }

    @Override
    public T get(int i) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException();
        }
        return array[i];
    }

    @Override
    public void set(int i, T data) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException();
        }
        array[i] = data;
    }

    @Override
    public void remove(T data) {
        int index = indexOf(data);
        if (index >= 0) {
            for (int i = index; i < array.length - 1; i++) {
                array[i] = array[i + 1];
            }
            n--;
        }
    }

    @Override
    public boolean isContain(T data) {
        return indexOf(data) >= 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    private int indexOf(T data) {
        for (int i = 0; i < n; i++) {
            if (array[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    private void enlarge() {
        int newSize = array.length * 2;
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator iterator = new Iterator() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < n;
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    return array[current++];
                }
                throw new NoSuchElementException();
            }
        };

        return iterator;
    }
}

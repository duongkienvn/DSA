package com.dsa.homework.revise.heap;

public interface PriorityQueueInterface<K, E> {
    int size();
    boolean isEmpty();
    void insert(Entry<K, E> entry);
    void insert(K k, E e);
    Entry<K, E> removeMin();
    Entry<K, E> min();
}

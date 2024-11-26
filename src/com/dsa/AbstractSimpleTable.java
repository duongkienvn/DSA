package com.dsa;

public abstract class AbstractSimpleTable<Key extends Comparable<Key>, Value> {

    // Phương thức trừu tượng cho các lớp con triển khai
    public abstract void put(Key key, Value value);

    public abstract Value get(Key key);

    public abstract boolean isEmpty();

    public abstract int size();

    public abstract Iterable<Key> keys();
}

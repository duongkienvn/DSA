package com.dsa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked", "deprecation"})
public class ArraySimpleTable<Key extends Comparable<Key>, Value> extends AbstractSimpleTable<Key, Value> {
    private Map<Key, Value> map;

    public ArraySimpleTable() {
        map = new HashMap<>();
    }

    @Override
    public void put(Key key, Value value) {
        // TODO Auto-generated method stub
        map.put(key, value);
    }

    @Override
    public Value get(Key key) {
        // TODO Auto-generated method stub
        return map.get(key);
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return map.isEmpty();
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return map.size();
    }

    @Override
    public Iterable<Key> keys() {
        // TODO Auto-generated method stub
        List<Key> list = map.entrySet()
                .stream()
                .map(k -> k.getKey())
                .collect(Collectors.toList());
        return list;
    }
}


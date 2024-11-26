package com.dsa.simpletable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")

public class OrderedArraySimpleTable<Key extends Comparable<Key>, Value> implements OrderedSimpleTable<Key, Value> {

    private Key[] keys;
    private Value[] values;
    int n = 0, default_size = 100;


    // Chú ý hoàn thiện hàm dựng, khởi tạo 2 mảng keys và values
    public OrderedArraySimpleTable() {
        keys = (Key[]) new Comparable[default_size];
        values = (Value[]) new Object[default_size];
        // TODO Auto-generated constructor stub
    }

    @Override
    //Phương thức thực hiện thêm 1 phần tử vào bảng tra cứu, phần tử mới được thêm vào sao
    // cho mảng keys luôn được sắp tăng dần
    public void put(Key key, Value value) {
        // TODO Auto-generated method stub
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        if (n >= keys.length) {
            enlarge();
        }

        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    public void enlarge() {
        Key[] newKeys = (Key[]) new Comparable[keys.length * 2];
        System.arraycopy(keys, 0, newKeys, 0, keys.length);
        keys = newKeys;
    }


    //Phương thức thực hiện tìm kiếm khóa key trên mảng keys bằng thuật toán tìm kiếm nhị phân
    // kết quả trả về là chỉ số (index) của phần tử key trong mảng key
    // nếu không tìm thấy key trong mảng keys, trả lại -1
    public int binarySearch(Key key) {
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (keys[mid].compareTo(key) == 0) {
                return mid;
            } else if (keys[mid].compareTo(key) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    // Phương thức get, lấy ra giá trị value tương ứng với key
    // Phương thức này gọi tới phương thức binarySearch(Key key)
    @Override
    public Value get(Key key) {
        // TODO Auto-generated method stub
        int index = binarySearch(key);

        return index != -1 ? values[index] : null;
    }


    @Override
    // xóa phần tử ra khỏi bảng tra cứu, dồn lại mảng sau khi xóa
    public void delete(Key key) {
        // TODO Auto-generated method stub
        int index = binarySearch(key);
        if (index == -1) {
            return;
        }
        for (int i = index; i < n - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
        keys[n] = null;
        values[n] = null;
        n--;
    }

    @Override
    public boolean contains(Key key) {
        // TODO Auto-generated method stub
        int index = binarySearch(key);
        return index != -1;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return n == 0;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return n;
    }

    @Override
    public Iterable<Key> keys() {
        // TODO Auto-generated method stub
        List<Key> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!keys[i].equals(null)) {
                list.add(keys[i]);
            }
        }
        return list;
    }

    @Override
    public Key min() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            return null;
        }
        return keys[0];
    }

    @Override
    public Key max() {
        // TODO Auto-generated method stub
        if (isEmpty()) {
            return null;
        }
        return keys[n - 1];
    }

    @Override
    public Key floor(Key key) {
        // TODO Auto-generated method stub
        Key result = null;
        for (int i = 0; i < n; i++) {
            if (keys[i].compareTo(key) <= 0) {
                result = keys[i];
            }
        }
        return result;
    }

    @Override
    public Key ceiling(Key key) {
        // TODO Auto-generated method stub
        Key result = null;
        for (int i = n - 1; i >= 0; i--) {
            if (keys[i].compareTo(key) >= 0) {
                result = keys[i];
            }
        }

        return result;
    }

    @Override
    public int rank(Key key) {
        // TODO Auto-generated method stub
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) high = mid - 1;
            else if (cmp > 0) low = mid + 1;
            else return mid;
        }
        return low;
    }

    @Override
    public Key select(int k) {
        // TODO Auto-generated method stub
        if (k < 0 || k >= n) return null;
        return keys[k];
    }

    @Override
    public void deleteMin() {
        // TODO Auto-generated method stub
        delete(keys[0]);
    }

    @Override
    public void deleteMax() {
        // TODO Auto-generated method stub
        delete(keys[n - 1]);
    }

    @Override
    public int size(Key u, Key v) {
        // TODO Auto-generated method stub
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (keys[i].compareTo(u) > 0 && keys[i].compareTo(v) < 0) {
                result++;
            }
        }

        return result;
    }

    @Override
    public Iterable<Key> keys(Key u, Key v) {
        // TODO Auto-generated method stub
        List<Key> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (keys[i].compareTo(u) > 0 && keys[i].compareTo(v) < 0) {
                list.add(keys[i]);
            }
        }

        return list;
    }

}


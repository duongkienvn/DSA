package com.dsa.midterm;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Integer> table;

    public SymbolTable() {
        table = new HashMap<>();
    }

    // Thêm một cặp ký tự vào bảng, tăng tần số nếu cặp đã tồn tại
    public void addPair(String pair) {
        table.put(pair, table.getOrDefault(pair, 0) + 1);
        // TODO: Implement this method
    }

    // Lấy tần số xuất hiện của cặp ký tự
    public int getFrequency(String pair) {
        // TODO: Implement this method
        return table.getOrDefault(pair, 0);
    }

    // Kiểm tra xem bảng có chứa cặp ký tự hay không
    public boolean containsPair(String pair) {
        // TODO: Implement this method
        return table.containsKey(pair);
    }

    // Xóa một cặp ký tự khỏi bảng
    public void removePair(String pair) {
        if (containsPair(pair)) {
            table.remove(pair);
        }

        // TODO: Implement this method
    }

    // Xóa toàn bộ bảng
    public void clearTable() {
        table.clear();
        // TODO: Implement this method
    }
}


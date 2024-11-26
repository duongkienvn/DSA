package com.dsa.midterm;

public class TestSymbolTable {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable();

        // Test addPair() và getFrequency()
        symbolTable.addPair("si");
        symbolTable.addPair("si");
        System.out.println("Tần số của 'si' (mong đợi: 2): " + symbolTable.getFrequency("si"));

        // Test containsPair()
        System.out.println("Bảng có chứa 'si' không? (mong đợi: true): " + symbolTable.containsPair("si"));
        System.out.println("Bảng có chứa 'ab' không? (mong đợi: false): " + symbolTable.containsPair("ab"));

        // Test removePair()
        symbolTable.removePair("si");
        System.out.println("Tần số của 'si' sau khi xóa (mong đợi: 0): " + symbolTable.getFrequency("si"));
        System.out.println("Bảng có chứa 'si' sau khi xóa không? (mong đợi: false): " + symbolTable.containsPair("si"));

        // Test clearTable()
        symbolTable.addPair("si");
        symbolTable.addPair("vi");
        symbolTable.clearTable();
        System.out.println("Tần số của 'si' sau khi xóa toàn bộ (mong đợi: 0): " + symbolTable.getFrequency("si"));
        System.out.println("Bảng có chứa 'vi' sau khi xóa toàn bộ không? (mong đợi: false): " + symbolTable.containsPair("vi"));
    }
}


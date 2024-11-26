package com.dsa.midterm;

public class ViFrequence {

    // Hàm kiểm tra thêm cặp ký tự vào bảng
    public boolean testAddPair() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.addPair("si");
        symbolTable.addPair("si");
        return symbolTable.getFrequency("si") == 2;
    }

    // Hàm kiểm tra tần số xuất hiện của cặp ký tự trong bảng
    public boolean testGetFrequency() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.addPair("si");
        return symbolTable.getFrequency("si") == 1;
    }

    // Hàm kiểm tra xem cặp ký tự có tồn tại trong bảng hay không
    public boolean testContainsPair() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.addPair("si");
        return symbolTable.containsPair("si") && !symbolTable.containsPair("ab");
    }

    // Hàm kiểm tra xóa một cặp ký tự khỏi bảng
    public boolean testRemovePair() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.addPair("si");
        symbolTable.removePair("si");
        return !symbolTable.containsPair("si") && symbolTable.getFrequency("si") == 0;
    }

    // Hàm kiểm tra xóa toàn bộ bảng
    public boolean testClearTable() {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.addPair("si");
        symbolTable.addPair("vi");
        symbolTable.clearTable();
        return symbolTable.getFrequency("si") == 0 && symbolTable.getFrequency("vi") == 0;
    }
}

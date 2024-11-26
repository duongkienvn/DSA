package com.dsa.homework.revise.heap;

public class HeapTest {
    public static void main(String[] args) {
        // Tạo một hàng đợi ưu tiên MinHeap với kích thước tối đa là 10
        MinHeapPriorityQueue<Integer, String> heap = new MinHeapPriorityQueue<>(10);

        // Chèn một số phần tử vào heap
        System.out.println("Thêm các phần tử vào heap:");
        heap.insert(5, "Năm");
        heap.insert(3, "Ba");
        heap.insert(8, "Tám");
        heap.insert(1, "Một");
        heap.insert(6, "Sáu");

        // Kiểm tra phần tử nhỏ nhất
        System.out.println("Phần tử nhỏ nhất hiện tại: " + heap.min().getValue()); // Kỳ vọng "Một"

        // Loại bỏ phần tử nhỏ nhất và in ra giá trị
        System.out.println("\nLoại bỏ các phần tử nhỏ nhất từng bước:");
        while (!heap.isEmpty()) {
            Entry<Integer, String> min = heap.removeMin();
            System.out.println("Đã loại bỏ phần tử có key: " + min.getKey() + ", value: " + min.getValue());
        }
    }
}


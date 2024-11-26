package com.dsa;

public class QuickSort {
    public double calculateAverage(int[] a) {
        int sum = 0;
        for (int num : a) {
            sum += num;
        }
        return sum / (double) a.length;
    }

    // Hàm tìm phần tử gần nhất với trung bình cộng
    public int findKeyClosestToAverage(int[] a, double average) {
        int key = a[0];
        double minDifference = Math.abs(a[0] - average);
        for (int i = 1; i < a.length; i++) {
            double difference = Math.abs(a[i] - average);
            if (difference < minDifference) {
                minDifference = difference;
                key = a[i];
            }
        }
        return key;
    }

    // Hàm thực hiện partition
    public int partition(int[] a, int key) {
        int i = -1;  // Chỉ số phần tử nhỏ hơn key
        for (int j = 0; j < a.length; j++) {
            if (a[j] < key) {
                i++;
                // Hoán đổi a[i] và a[j]
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        // Tìm vị trí cuối cùng của key sau khi partition
        for (int k = 0; k < a.length; k++) {
            if (a[k] == key) {
                return k;  // Trả về vị trí của key
            }
        }

        return -1;  // Nếu không tìm thấy
    }

    public static void main(String[] args) {
        QuickSort q = new QuickSort();
        int[] a = {1, 2, 3, 4, 1, 3, 5, 6, 10};

        // Tính trung bình cộng
        double average = q.calculateAverage(a);

        // Tìm phần tử gần nhất với trung bình cộng
        int key = q.findKeyClosestToAverage(a, average);

        // Partition theo key
        int keyIndex = q.partition(a, key);

        // In kết quả
        System.out.println("Key = " + a[keyIndex] + " tại vị trí " + keyIndex);
        System.out.print("Mảng sau khi partition: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}

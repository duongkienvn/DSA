package com.dsa.homework.thirdlab.queue;

import java.util.Iterator;

public class QueueTest {
    public static void main(String[] args) {
        testArrayQueue();
    }

    public static void testArrayQueue() {
        // Khởi tạo hàng đợi với kích thước 5
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);

        // Kiểm tra khi hàng đợi rỗng
        System.out.println("Queue is empty: " + queue.isEmpty()); // Expected: true

        // Thêm phần tử vào hàng đợi
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        Iterator<Integer> it = queue.iterator();

        System.out.println("Elements in queue using Iterator:");
        while (it.hasNext()) {
            System.out.print(it.next() + " "); // Expected: 10 20 30 40 50
        }
        System.out.println();

        // Kiểm tra hàng đợi sau khi thêm
        System.out.println("Queue is empty: " + queue.isEmpty()); // Expected: false

        // Lấy phần tử đầu tiên (FIFO - 10)
        System.out.println("Dequeued element: " + queue.dequeue()); // Expected: 10

        // Lấy tiếp phần tử đầu tiên sau khi đã dequeue (FIFO - 20)
        System.out.println("Dequeued element: " + queue.dequeue()); // Expected: 20

        // Thêm một phần tử mới vào hàng đợi
        queue.enqueue(60);

        // Duyệt qua các phần tử còn lại trong hàng đợi (FIFO)
        System.out.println("Remaining elements in queue:");
        for (Integer i : queue) {
            System.out.print(i + " "); // Expected: 30 40 50 60
        }
        System.out.println();

        // Kiểm tra dequeue cho đến khi hết phần tử
        System.out.println("Dequeued element: " + queue.dequeue()); // Expected: 30
        System.out.println("Dequeued element: " + queue.dequeue()); // Expected: 40
        System.out.println("Dequeued element: " + queue.dequeue()); // Expected: 50
        System.out.println("Dequeued element: " + queue.dequeue()); // Expected: 60

        // Kiểm tra hàng đợi sau khi xóa hết phần tử
        System.out.println("Queue is empty: " + queue.isEmpty()); // Expected: true
    }
}


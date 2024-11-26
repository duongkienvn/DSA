package com.dsa.homework.sixlab.ex1;

public class PriorityQueueTest {
    public static void main(String[] args) {
        System.out.println("Testing Integer Priority Queue:");
        testIntegerPriorityQueue();

        System.out.println("\nTesting Custom Object Priority Queue:");
        testCustomObjectPriorityQueue();
    }

    public static void testIntegerPriorityQueue() {
        PriorityQueueInterface<Integer, String> pq = new SortedLinkedPriorityQueue<>();

        pq.insert(4, "Four");
        pq.insert(1, "One");
        pq.insert(3, "Three");
        pq.insert(2, "Two");

        while (!pq.isEmpty()) {
            Entry<Integer, String> entry = pq.removeMin();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    public static void testCustomObjectPriorityQueue() {
        class Product {
            String name;
            int price;

            Product(String name, int price) {
                this.name = name;
                this.price = price;
            }

            @Override
            public String toString() {
                return "Product{name='" + name + "', price=" + price + '}';
            }
        }

        PriorityQueueInterface<Integer, Product> pq = new SortedLinkedPriorityQueue<>();
        pq.insert(200, new Product("TV", 200));
        pq.insert(150, new Product("Radio", 150));
        pq.insert(300, new Product("Laptop", 300));

        while (!pq.isEmpty()) {
            Entry<Integer, Product> entry = pq.removeMin();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}


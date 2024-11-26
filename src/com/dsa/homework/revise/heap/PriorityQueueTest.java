package com.dsa.homework.revise.heap;


public class PriorityQueueTest {

    public static void main(String[] args) {
        // Test with integer list (element value as key)
        PriorityQueueInterface<Integer, String> intQueue = new UnsortedArrayPriorityQueue<>();
        intQueue.insert(3, "three");
        intQueue.insert(1, "one");
        intQueue.insert(2, "two");

        System.out.println("Minimum: " + intQueue.min().getValue()); // Should print "one"

        // Test with objects having different key and value
        PriorityQueueInterface<Double, String> productQueue = new SortedArrayPriorityQueue<>();
        productQueue.insert(100.0, "Product A");
        productQueue.insert(200.0, "Product B");
        productQueue.insert(150.0, "Product C");

        System.out.println("Minimum: " + productQueue.min().getValue()); // Should print "Product A"
        testCustomObjectPriorityQueue();
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


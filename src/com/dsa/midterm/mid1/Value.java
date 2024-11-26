package com.dsa.midterm.mid1;

import java.util.HashMap;
import java.util.Map;

public class Value {
    public long getMaxValue(int[] v) {
        long sum = 0;
        long maxVal = Long.MIN_VALUE;

        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : v) {
            sum += i;
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            long temp = sum - (entry.getValue() * entry.getKey());
            maxVal = Math.max(maxVal, temp);
        }

        return maxVal;
    }

    public long getMinValue(int[] v) {
        long sum = 0;
        long minVal = Long.MAX_VALUE;

        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : v) {
            sum += i;
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            long temp = sum - (entry.getValue() * entry.getKey());
            minVal = Math.min(minVal, temp);
        }

        return minVal;
    }
}

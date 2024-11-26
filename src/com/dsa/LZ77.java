package com.dsa;

import java.util.ArrayList;
import java.util.List;

public class LZ77 {
    /**
     * This function takes an input array t, the current position in the array,
     * and the size of the window. It returns the largest Occurrence found in the window.
     * Don't forget that the current character is not in the window.
     * @param t
     * @param currentPosition
     * @param windowSize
     * @return an occurrence
     */

    public static Occurrence longestOccurrence(int[] t, int currentPosition, int windowSize) {
        // TODO: Your code here

        int bestRetour = 0; // khoảng cách tốt nhất về phía sau
        int bestSize = 0; // kích thước tốt nhất của chuỗi lặp lại

        // Cửa sổ chỉ nằm trước vị trí hiện tại
        int windowStart = Math.max(0, currentPosition - windowSize);

        for (int i = windowStart; i < currentPosition; i++) {
            int matchSize = 0;
            // So sánh từng ký tự trong cửa sổ với chuỗi bắt đầu từ vị trí hiện tại
            while (i + matchSize < currentPosition && currentPosition + matchSize < t.length &&
                    t[i + matchSize] == t[currentPosition + matchSize]) {
                matchSize++;
            }

            if (matchSize > bestSize) {
                bestSize = matchSize;
                bestRetour = currentPosition - i;
            }
        }

        return new Occurrence(bestRetour, bestSize);
    }

    /**
     * Computes the length of the compressed array.
     * @param t
     * @param windowSize
     * @return the length of the compressed array.
     */
    public static int length(int[] t, int windowSize) {
        // TODO: Your code here

        int length = 0;
        int i = 0;

        while (i < t.length) {
            Occurrence occurrence = longestOccurrence(t, i, windowSize);
            if (occurrence.size > 0) {
                length += 1; // Một phần tử nén
                i += occurrence.size; // Di chuyển theo kích thước chuỗi lặp lại
            } else {
                length += 1; // Một ký tự chưa nén
                i++;
            }
        }

        return length;
    }

    /**
     * Compresses an array t.
     * @param t
     * @param windowSize
     * @return an array of Element.
     */
    public static Element[] compress(int[] t, int windowSize) {
        List<Element> compressed = new ArrayList<>();
        int i = 0;

        while (i < t.length) {
            Occurrence occurrence = longestOccurrence(t, i, windowSize);
            if (occurrence.size > 0) {
                compressed.add(new Element(occurrence, t[i + occurrence.size])); // Chuỗi và ký tự tiếp theo
                i += occurrence.size + 1;
            } else {
                compressed.add(new Element(new Occurrence(0, 0), t[i]));
                i++;
            }
        }

        return compressed.toArray(new Element[0]);
    }

    /**
     * Prints out the compression array.
     * @param t
     */
    public static void printCompression(Element[] t) {
        StringBuilder sb = new StringBuilder(1024);
        for (Element e : t) {
            sb.append(e.toString());
        }
        System.out.println(sb);
    }

    /**
     * Computes the length of the decompressed data, given the
     * compressed data.
     * @param t
     * @return a length
     */
    public static int lengthInverse(Element[] t) {

        int length = 0;

        for (Element e : t) {
            length += e.o.size + 1; // Cộng cả chuỗi và ký tự tiếp theo
        }

        return length;
    }

    /**
     * Decompresses data.
     * @param t
     * @return an array representing decompressed data
     */
    public static int[] decompress(Element[] t) {
        List<Integer> decompressed = new ArrayList<>();

        for (Element e : t) {
            Occurrence o = e.o;
            if (o.size > 0) {
                int start = decompressed.size() - o.retour;
                for (int i = 0; i < o.size; i++) {
                    decompressed.add(decompressed.get(start + i));
                }
            }
            decompressed.add(e.s);
        }

        return decompressed.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Copy the characters of array t1, going from start1 to start1 + len - 1
     * to the array t2, starting from position start2.
     * @param t1
     * @param t2
     * @param start1
     * @param len
     * @param start2
     */
    static void blit(int[] t1, int[] t2, int start1, int len, int start2) {
        // TODO: Your code here
        for (int i = 0; i < len; i++) {
            t2[start2 + i] = t1[start1 + i];
        }
    }

    public static void printDecompression(int[] t) {
        for (int i : t) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }
}

class Element {
    Occurrence o;
    int s;

    Element(Occurrence o, int s) {
        this.o = o;
        this.s = s;
    }

    @Override
    public String toString() {
        return o.toString() + s;
    }
}

class Occurrence {
    int retour;
    int size;

    Occurrence (int retour, int size) {
        this.retour = retour;
        this.size = size;
    }

    @Override
    public String toString() {
        return "(" + retour + "," + size + ")";
    }
}


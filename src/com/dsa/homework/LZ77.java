package com.dsa.homework;

import java.util.ArrayList;
import java.util.List;

public class LZ77 {
    /**
     * This function takes an input array t, the current position in the array,
     * and the size of the window. It returns the largest Occurrence found in the window.
     * Don't forget that the current character is not in the window.
     *
     * @param t
     * @param currentPosition
     * @param windowSize
     * @return an occurrence
     */

    public static Occurrence longestOccurrence(int[] t, int currentPosition, int windowSize) {
        // TODO: Your code here
        Occurrence occurrence = new Occurrence(0, 0);
        int start = Math.max(0, currentPosition - windowSize);

        int maxSize = 0;
        int retour = 0;

        for (int i = start; i < currentPosition; i++) {
            if (t[i] == t[currentPosition]) {
                int size = 0;
                int j = i, k = currentPosition;
                while (j < currentPosition) {
                    if (t[j++] == t[k++]) {
                        size++;
                    } else {
                        break;
                    }
                }
                if (maxSize < size) {
                    maxSize = size;
                    retour = currentPosition - i;
                }
            }
        }

        occurrence.retour = retour;
        occurrence.size = maxSize;

        return occurrence;
    }

    /**
     * Computes the length of the compressed array.
     *
     * @param t
     * @param windowSize
     * @return the length of the compressed array.
     */
    public static int length(int[] t, int windowSize) {
        // TODO: Your code here
        int length = 0;
        if (t.length == 0) {
            return length;
        }

        for (int i = 0; i < t.length; i++) {
            Occurrence longestOccur = longestOccurrence(t, i, windowSize);
            if (longestOccur.size > 0) {
                length += 1;
                i += longestOccur.size;
            } else {
                length += 1;
            }
        }

        return length;
    }

    /**
     * Compresses an array t.
     *
     * @param t
     * @param windowSize
     * @return an array of Element.
     */
    public static Element[] compress(int[] t, int windowSize) {
        // TODO: Your code here
        int length = length(t, windowSize);
        Element[] elements = new Element[length];
        int idx = 0;
        for (int i = 0; i < t.length; i++) {
            Occurrence occurrence = longestOccurrence(t, i, windowSize);
            if (occurrence.size > 0) {
                i += occurrence.size;
            }
            Element element = null;
            element = new Element(occurrence, t[i]);
            elements[idx++] = element;
        }

        return elements;
    }

    /**
     * Prints out the compression array.
     *
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
     *
     * @param t
     * @return a length
     */
    public static int lengthInverse(Element[] t) {
        if (t.length == 0) {
            return 0;
        }

        int length = 0;
        for (Element element : t) {
            length += element.o.size + 1;
        }

        return length;
    }

    /**
     * Decompresses data.
     *
     * @param t
     * @return an array representing decompressed data
     */
    public static int[] decompress(Element[] t) {
        // TODO: Your code here
        List<Integer> result = new ArrayList<>();

        for (Element element : t) {
            Occurrence occurrence = element.o;
            if (occurrence.size > 0) {
                int start = result.size() - occurrence.retour;
                for (int i = 0; i < occurrence.size; i++) {
                    result.add(result.get(start + i));
                }
            }
            result.add(element.s);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Copy the characters of array t1, going from start1 to start1 + len - 1
     * to the array t2, starting from position start2.
     *
     * @param t1
     * @param t2
     * @param start1
     * @param len
     * @param start2
     */
    static void blit(int[] t1, int[] t2, int start1, int len, int start2) {
        // TODO: Your code here
        System.arraycopy(t1, start1, t2, start2, start1 + len);
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

    Occurrence(int retour, int size) {
        this.retour = retour;
        this.size = size;
    }

    @Override
    public String toString() {
        return "(" + retour + "," + size + ")";
    }
}


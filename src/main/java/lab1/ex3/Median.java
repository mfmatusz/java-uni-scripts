package lab1.ex3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Lab 1 / Exercise 3 — Median via Bubble Sort
 *
 * Demonstrates finding and removing the median from a list of integers
 * after sorting with bubble sort.
 */
public class Median {

    /** Returns the median of a non-empty sorted list. */
    public static int findMedian(LinkedList<Integer> sorted) {
        int size = sorted.size();
        int mid = size / 2;
        if (size % 2 == 0) {
            return (sorted.get(mid - 1) + sorted.get(mid)) / 2;
        }
        return sorted.get(mid);
    }

    /**
     * Removes the lower median element from a sorted list.
     * For even-sized lists the lower of the two middle elements is removed.
     */
    public static void deleteMedian(LinkedList<Integer> sorted) {
        int mid = sorted.size() / 2;
        if (sorted.size() % 2 == 0) {
            sorted.remove(mid - 1);
        } else {
            sorted.remove(mid);
        }
    }

    /** In-place bubble sort (ascending). */
    public static void bubbleSort(LinkedList<Integer> list) {
        int n = list.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    int tmp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, tmp);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(
                List.of(42, 7, 99, 3, 56, 21, 85)
        );
        bubbleSort(list);
        System.out.println("Sorted: " + list);
        System.out.println("Median: " + findMedian(list));
    }
}

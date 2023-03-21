package lab1.ex3;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MedianTest {

    @Test
    void bubbleSortProducesSortedList() {
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 8, 1, 9, 3));
        Median.bubbleSort(list);
        assertEquals(List.of(1, 2, 3, 5, 8, 9), list);
    }

    @Test
    void bubbleSortAlreadySortedList() {
        LinkedList<Integer> list = new LinkedList<>(List.of(1, 2, 3));
        Median.bubbleSort(list);
        assertEquals(List.of(1, 2, 3), list);
    }

    @Test
    void medianOfOddLengthList() {
        LinkedList<Integer> sorted = new LinkedList<>(List.of(1, 3, 5, 7, 9));
        assertEquals(5, Median.findMedian(sorted));
    }

    @Test
    void medianOfEvenLengthListIsAverageOfMiddleTwo() {
        LinkedList<Integer> sorted = new LinkedList<>(List.of(1, 3, 5, 7));
        // (3 + 5) / 2 = 4
        assertEquals(4, Median.findMedian(sorted));
    }

    @Test
    void deleteMedianFromOddList() {
        LinkedList<Integer> sorted = new LinkedList<>(List.of(1, 3, 5, 7, 9));
        Median.deleteMedian(sorted); // removes 5 (index 2)
        assertEquals(List.of(1, 3, 7, 9), sorted);
    }

    @Test
    void deleteMedianFromEvenListRemovesLowerMiddle() {
        LinkedList<Integer> sorted = new LinkedList<>(List.of(1, 3, 5, 7));
        Median.deleteMedian(sorted); // removes 3 (index 1, lower middle)
        assertEquals(List.of(1, 5, 7), sorted);
    }
}

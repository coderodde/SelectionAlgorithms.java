package com.github.coderodde.algo.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * This class provides common methods for dealing with the order statistics 
 * algorithms.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 14, 2022; Love Edition)
 * @since 1.6 (Feb 14, 2022; Love Edition)
 */
public final class Support {

    public static <E> 
        void checkArrayContainsNoDuplicatesElements(E[] array,
                                                    int fromIndex, 
                                                    int toIndex) {
        Set<E> filter = new HashSet<>();

        for (E element : array) {
            if (filter.contains(element)) {
                throw new IllegalArgumentException(
                        "The element [" + element + "] is duplicated.");
            }

            filter.add(element);
        }
    }

    public static <E> void checkArrayContainsNoDuplicatesElements(E[] array) {
        checkArrayContainsNoDuplicatesElements(array, 0, array.length);
    }

    public static Integer[] getArray(Random random, 
                                     int length,
                                     int minimumValue,
                                     int maximumValue) {

        Set<Integer> filter = new HashSet<>();

        while (filter.size() < length) {
            int datum = minimumValue 
                      + random.nextInt(maximumValue - minimumValue + 1);

            filter.add(datum);
        }

        List<Integer> ints = new ArrayList<>(filter);
        Collections.<Integer>shuffle(ints, random);

        return ints.toArray(new Integer[length]);
    }

    static <E> void swap(E[] array, int index1, int index2) {
        E tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    static <E> void checkArray(E[] array) {
        Objects.requireNonNull(array, "The input array is null.");

        if (array.length == 0) {
            throw new IllegalArgumentException("The input array is empty.");
        }
    }

    static void checkK(int rangeLength, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("'k' is negative: " + k);
        }

        if (k >= rangeLength) {
            throw new IllegalArgumentException(
                    "'k' is too large: " + k + 
                    "Must be at most " + (rangeLength - 1) + ".");
        }
    }

    static void checkRangeIndices(int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IllegalArgumentException(
                    "frontIndex is negative: " 
                            + fromIndex
                            + ". Must be at least 0.");
        }

        if (toIndex < 0) {
            throw new IllegalArgumentException(
                    "toIndex is negative: " 
                            + toIndex
                            + ". Must be at least 0.");
        }

        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(
                    "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
    }
}

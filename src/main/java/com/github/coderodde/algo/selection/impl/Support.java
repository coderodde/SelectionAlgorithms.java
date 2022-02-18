package com.github.coderodde.algo.selection.impl;

import java.util.Objects;

/**
 * This class provides common methods for dealing with the order statistics 
 * algorithms.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Feb 14, 2022; Love Edition)
 * @since 1.6 (Feb 14, 2022; Love Edition)
 */
public final class Support {

    /**
     * Partition the input array around the pivot value.
     * @param <E> the array component type.
     * @param array the array to partition.
     * @param pivot the pivot value.
     * @return the number of array elements no larger than the pivot.
     */
    static <E extends Comparable<? super E>> int partition(E[] array,
                                                           E pivot) {
        int i = -1;
        
        for (int j = 0; j < array.length - 1; ++j) {
            if (array[j].compareTo(pivot) <= 0) {
                ++i;
                swap(array, i, j);
            }
        }
        
        swap(array, i + 1, array.length - 1);
        return i + 1;
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
    
    static void checkK(int arrayLength, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("'k' is negative: " + k);
        }
        
        if (k >= arrayLength) {
            throw new IllegalArgumentException(
                    "'k' is too large: " + k + 
                    "Must be at most " + (arrayLength - 1) + ".");
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

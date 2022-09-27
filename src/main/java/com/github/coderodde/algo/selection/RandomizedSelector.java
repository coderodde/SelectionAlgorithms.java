package com.github.coderodde.algo.selection;

import static com.github.coderodde.algo.selection.Support.checkArray;
import static com.github.coderodde.algo.selection.Support.checkK;
import static com.github.coderodde.algo.selection.Support.checkRangeIndices;
import static com.github.coderodde.algo.selection.Support.swap;
import java.util.Random;

/**
 * This class implements a randomized selector relying on the ideas of 
 * quicksort.
 * 
 * @param <E> the array component type. Must be {@link Comparable}.
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Sep 26, 2022)
 * @since 1.6 (Sep 26, 2022)
 */
public final class RandomizedSelector<E extends Comparable<? super E>> implements Selector<E> {

    @Override
    public E select(E[] array, int k) {
        checkArray(array);
        return select(array, k, 0, array.length);
    }

    @Override
    public E select(E[] array, int index, int fromIndex, int toIndex) {
        checkArray(array);
        checkRangeIndices(fromIndex, toIndex);
        checkK(toIndex - fromIndex, index);

        Random random = new Random();
        return selectImpl(array, index, fromIndex, toIndex, random);
    }

    private E selectImpl(E[] array, 
                         int index, 
                         int fromIndex, 
                         int toIndex,
                         Random random) {
        int rangeLength = toIndex - fromIndex;

        if (rangeLength < 1) {
            throw new IllegalArgumentException(
                    "The requested range is non-existent, length = " 
                            + rangeLength);
        }

        if (rangeLength == 1) {
            return array[fromIndex];
        }

        int q = randomizedPartition(array, fromIndex, toIndex, random);

        int k = q - fromIndex;

        if (index == k) {
            return array[q];
        } else if (index < k) {
            return selectImpl(array, index, fromIndex, q, random);
        } else {
            return selectImpl(array, index - k, q, toIndex, random);
        }
    }

    private static <E extends Comparable<? super E>> 
        int randomizedPartition(E[] array,
                                int fromIndex, 
                                int toIndex,
                                Random random) {

        int i = fromIndex + random.nextInt(toIndex - fromIndex);
        swap(array, i, toIndex - 1);
        return partition(array, fromIndex, toIndex);
    }

    static <E extends Comparable<? super E>> 
        int partition(E[] array, int fromIndex, int toIndex) {
        E x = array[toIndex - 1];
        int i = fromIndex - 1;

        for (int j = fromIndex; j < toIndex - 1; j++) {
            if (array[j].compareTo(x) <= 0) {
                swap(array, ++i, j);
            }
        }

        swap(array, i + 1, toIndex - 1);
        return i + 1;
    }
}

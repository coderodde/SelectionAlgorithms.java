package com.github.coderodde.algo.selection;

import static com.github.coderodde.algo.selection.Support.checkArray;
import static com.github.coderodde.algo.selection.Support.checkK;
import static com.github.coderodde.algo.selection.Support.checkRangeIndices;
import java.util.Arrays;

/**
 * 
 * @author Rodion "rodde" Efremov
 * @param <E> the array component type.
 * @version 1.6 (Sep 25, 2022)
 * @since 1.6 (Sep 25, 2022)
 */
public final class SortingSelector<E extends Comparable<? super E>>
        implements Selector<E> {

    @Override
    public E select(E[] array, int k, int fromIndex, int toIndex) {
        checkArray(array);
        checkRangeIndices(fromIndex, toIndex);
        checkK(toIndex - fromIndex, k);
        Arrays.sort(array, fromIndex, toIndex);
        return array[k + fromIndex];
    }
    
    @Override
    public E select(E[] array, int k) {
        checkArray(array);
        return select(array, k, 0, array.length);
    }
}

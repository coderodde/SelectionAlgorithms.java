package com.github.coderodde.algo.selection.impl;

import com.github.coderodde.algo.selection.Selector;
import static com.github.coderodde.algo.selection.impl.Support.checkArray;
import static com.github.coderodde.algo.selection.impl.Support.checkK;
import static com.github.coderodde.algo.selection.impl.Support.checkRangeIndices;
import java.util.Arrays;

/**
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 ()
 * @param <E>
 * @since 1.6 ()
 */
public final class SortingSelector<E extends Comparable<? super E>>
        implements Selector<E> {

    @Override
    public E select(E[] array, int k, int fromIndex, int toIndex) {
        checkArray(array);
        checkK(array.length, k);
        checkRangeIndices(fromIndex, toIndex);
        Arrays.sort(array);
        return array[k];
    }
    
    @Override
    public E select(E[] array, int k) {
        checkArray(array);
        return select(array, k, 0, array.length);
    }
}

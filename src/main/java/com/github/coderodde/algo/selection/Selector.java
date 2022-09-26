package com.github.coderodde.algo.selection;

/**
 * This interface defines the API for the selection algorithms.
 * 
 * @author Rodion "rodde" Efremov
 * @param <E> the array component type.
 * @version 1.6 (Sep 25, 2022)
 * @since 1.6 (Sep 25, 2022)
 */
public sealed interface Selector<E extends Comparable<? super E>> 
        permits LinearTimeSelector, SortingSelector, RandomizedSelector {
    
    E select(E[] array, int k);
    E select(E[] array, int k, int fromIndex, int toIndex);
}

package com.github.coderodde.algo.selection;

/**
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 ()
 * @since 1.6 ()
 */
public interface Selector<E extends Comparable<? super E>> {

    E select(E[] array, int k);
}

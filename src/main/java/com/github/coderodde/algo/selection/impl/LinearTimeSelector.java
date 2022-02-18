package com.github.coderodde.algo.selection.impl;

import com.github.coderodde.algo.selection.Selector;
import static com.github.coderodde.algo.selection.impl.Support.checkK;
import static com.github.coderodde.algo.selection.impl.Support.partition;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class provides a method for {@code k}th order statistics in worst case
 * linear time.
 * 
 * @author Rodion "rodde" Efremov
 * @param <E> the element type.
 * @version 1.6 (Feb 18, 2022)
 * @since 1.6 (Feb 18, 2022)
 */
public class LinearTimeSelector<E extends Comparable<? super E>> 
implements Selector<E> {
    
    private static final int GROUP_LENGTH = 5;

    /**
     * {@inheritDoc }
     */
    @Override
    public E select(E[] array, int k) {
        Objects.requireNonNull(array, "The input array is null.");
        
        if (array.length == 0) {
            throw new IllegalArgumentException("The input array is empty.");
        }
        
        checkK(array.length, k);
        return selectImpl(array, k, 0, array.length);
    }
    
    private static <E extends Comparable<? super E>> E 
        selectImpl(E[] array, int i, int fromIndex, int toIndex) {
           
        int subarrayLength = toIndex - fromIndex;
            
        if (subarrayLength == 1) {
            return array[fromIndex];
        }
        
        E[] medians = getGroupMedians(array, 
                                      fromIndex,
                                      toIndex);
        
        int mediansLength =  medians.length;
        int medianIndex   = (mediansLength - 1) / 2;
        
        E x = selectImpl(medians, 
                         medianIndex,
                         0, 
                         medians.length);
        
        int k = partition(array, x);
        
        if (fromIndex + i == k - 1) {
            return x;
        }
        
        return fromIndex + i < k ? selectImpl(array, i, fromIndex, k) : 
                                   selectImpl(array, i - k, k, toIndex);
    }
    
    private static <E extends Comparable<? super E>>
        E[] getGroupMedians(E[] array, int fromIndex, int toIndex) {
            
        int arrayLength = toIndex - fromIndex;
        int numberOfGroups = arrayLength / 5 + 
                            (arrayLength % 5 != 0 ? 1 : 0);
        
        E[] outputArray = Arrays.copyOfRange(array, 0, numberOfGroups);
        
        int groupStartIndex  = fromIndex;
        int outputArrayIndex = 0;
        
        for (int groupIndex = 0; groupIndex < numberOfGroups; groupIndex++) {
            int groupEndIndex = 
                    Math.min(groupStartIndex + GROUP_LENGTH, toIndex);
            
            Arrays.sort(array, groupStartIndex, groupEndIndex);
            int groupLength = groupEndIndex - groupStartIndex;
            int medianIndex = (groupLength - 1) / 2;
            
            outputArray[outputArrayIndex++] = array[groupStartIndex +
                                                    medianIndex];
            groupStartIndex = groupEndIndex;
        }
        
        return outputArray;
    }
}

package com.github.coderodde.algo.selection.impl;

import com.github.coderodde.algo.selection.Selector;
import static com.github.coderodde.algo.selection.impl.Support.checkK;
import static com.github.coderodde.algo.selection.impl.Support.partition;
import java.util.Arrays;
import java.util.Objects;

/**
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 ()
 * @since 1.6 ()
 */
public class LinearTimeSelector<E extends Comparable<? super E>> 
implements Selector<E> {
    
    private static final int GROUP_LENGTH = 5;

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
           
        if (array.length == 1) {
            return array[0];
        }
        
        E[] medians = getGroupMedians(array, 
                                      fromIndex,
                                      toIndex);
        
        E x = selectImpl(medians, 
                         i,
                         fromIndex, 
                         toIndex);
        
        int k = partition(array, x);
        
        if (i == k) {
            return x;
        }
        
        return i < k ? selectImpl(array, i, fromIndex, fromIndex + k) : 
                       selectImpl(array, i - k, toIndex - k, toIndex);
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
            outputArray[outputArrayIndex++] = array[medianIndex];
        }
        
        return outputArray;
    }
}

package com.github.coderodde.algo.selection.impl;

import com.github.coderodde.algo.selection.Selector;
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
        return selectImpl(array, k)[0];
    }
    
    private static <E extends Comparable<? super E>> E[] 
        selectImpl(E[] array, int i) {
           
        if (array.length == 1) {
            return Arrays.copyOfRange(array, 0, 1);
        }
        
        E[] medians = getGroupMedians(array);
        E x = selectImpl(medians, i)[0];
        int k = partition(array, x);
        
        if (i == k) {
            E[] returnArray = Arrays.copyOfRange(array, 0, 1);
            returnArray[0] = x;
            return returnArray;
        }
        
        return i < k ? selectImpl(medians, i) : 
                       selectImpl(medians, i - k);
    }
    
    
    private static <E extends Comparable<? super E>>
        E[] getGroupMedians(E[] array) {
            
        int arrayLength = array.length;
        int numberOfGroups = arrayLength / 5 + 
                            (arrayLength % 5 != 0 ? 1 : 0);
        
        E[] outputArray = Arrays.copyOfRange(array, 0, numberOfGroups);
        
        int groupStartIndex  = 0;
        int outputArrayIndex = 0;
        
        for (int groupIndex = 0; groupIndex < numberOfGroups; groupIndex++) {
            int groupEndIndex = 
                    Math.min(groupStartIndex + GROUP_LENGTH,
                             arrayLength);
            
            Arrays.sort(array, groupStartIndex, groupEndIndex);
            int groupLength = groupEndIndex - groupStartIndex;
            int medianIndex = (groupLength - 1) / 2;
            outputArray[outputArrayIndex++] = array[medianIndex];
        }
        
        return outputArray;
    }
    
    private static void checkK(int arrayLength, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("'k' is negative: " + k);
        }
        
        if (k >= arrayLength) {
            throw new IllegalArgumentException(
                    "'k' is too large: " + k + 
                    "Must be at most " + (arrayLength - 1) + ".");
        }
    }
}

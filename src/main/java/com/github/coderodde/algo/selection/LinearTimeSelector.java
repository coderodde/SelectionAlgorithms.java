package com.github.coderodde.algo.selection;

import static com.github.coderodde.algo.selection.Support.checkArray;
import static com.github.coderodde.algo.selection.Support.checkK;
import static com.github.coderodde.algo.selection.Support.checkRangeIndices;
import java.util.Arrays;

/**
 * This class provides a method for {@code k}th order statistics in worst case
 * linear time.
 * 
 * @author Rodion "rodde" Efremov
 * @param <E> the element type.
 * @version 1.6 (Feb 18, 2022)
 * @since 1.6 (Feb 18, 2022)
 */
public final class LinearTimeSelector<E extends Comparable<? super E>> 
implements Selector<E> {
    
    private static final int GROUP_LENGTH = 5;

    /**
     * {@inheritDoc }
     */
    @Override
    public E select(E[] array, int k, int fromIndex, int toIndex) {
        checkArray(array);
        checkK(array.length, k);
        checkRangeIndices(fromIndex, toIndex);
        return selectImpl(array, k, fromIndex, toIndex);
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public E select(E[] array, int k) {
        checkArray(array);
        return select(array, k, 0, array.length);
    }
    
    private static <E extends Comparable<? super E>> E 
        selectImpl(E[] array, int index, int fromIndex, int toIndex) {
        E[] medians = getGroupMedians(array, 
                                      fromIndex,
                                      toIndex);
        
        E pivot;
        
        if (medians.length <= 5) {
            pivot = medians[medians.length / 2];
        } else {
            pivot = selectImpl(medians, medians.length / 2, 0, medians.length);
        }
        
        int leftArrayLength = 0;
        int rightArrayLength = 0;
        
        for (int i = fromIndex; i < toIndex; i++) {
            E datum = array[i];
            
            if (datum.compareTo(pivot) < 0) {
                leftArrayLength++;
            } else if (datum.compareTo(pivot) > 0) {
                rightArrayLength++;
            }
        }
        
        E[] leftArray = Arrays.copyOf(array, leftArrayLength);
        E[] rightArray = Arrays.copyOf(array, rightArrayLength);
       
        for (int i = fromIndex, outputIndex = 0; i < toIndex; i++) {
            E datum = array[i];
            
            if (datum.compareTo(pivot) < 0) {
                leftArray[outputIndex++] = datum;
            }
        }
        
        for (int i = fromIndex, outputIndex = 0; i < toIndex; i++) {
            E datum = array[i];
            
            if (datum.compareTo(pivot) > 0) {
                rightArray[outputIndex++] = datum;
            }
        }
        
        int k = leftArrayLength;
        
        if (index < k) {
            return selectImpl(leftArray, index, 0, leftArrayLength);
        } else if (index > k) {
            return selectImpl(rightArray, index - k - 1, 0, rightArrayLength);
        } else {
            return pivot;
        }
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

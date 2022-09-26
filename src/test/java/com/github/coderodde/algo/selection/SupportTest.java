package com.github.coderodde.algo.selection;

import org.junit.Test;
import static org.junit.Assert.*;

public class SupportTest {
    
    @Test
    public void testPartition() {
        Integer[] array = { 4, 1, 2, 6, 3 };
        int index = Support.partition(array, 3);
        assertEquals(2, index);
        
        array = new Integer[] { 10, 4, 2, 1, 4, 6, 5  };
        index = Support.partition(array, 5);
        assertEquals(4, index);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void throwOnDuplicateElementInArray() {
        Integer[] array = { 2, 3, 4, 5, 1, -1, 3 };
        Support.checkArrayContainsNoDuplicatesElements(array);
    }
}

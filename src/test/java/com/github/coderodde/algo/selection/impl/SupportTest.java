package com.github.coderodde.algo.selection.impl;

import org.junit.Test;
import static org.junit.Assert.*;

public class SupportTest {
    
    @Test
    public void testPartition() {
        Integer[] array = { 4, 1, 2, 6, 3 };
        int index = Support.partition(array, 3);
        assertEquals(2, index);
        
        array = new Integer[] { 2, 2, 2, 2 };
        index = Support.partition(array, 2);
        assertEquals(3, index);
        
        array = new Integer[] { 10, 4, 2, 1, 4, 6, 5  };
        index = Support.partition(array, 5);
        assertEquals(4, index);
    }
}

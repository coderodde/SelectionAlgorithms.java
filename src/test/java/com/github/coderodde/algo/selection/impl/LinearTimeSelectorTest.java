package com.github.coderodde.algo.selection.impl;

import com.github.coderodde.algo.selection.Selector;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinearTimeSelectorTest {
    
    private final Selector<Integer> selector = new LinearTimeSelector<>();
    
    @Test
    public void testSelect() {
        Integer[] array = { 4, 1, 5, 2, 3 };
        assertEquals(Integer.valueOf(1), selector.select(array, 0));
        assertEquals(Integer.valueOf(2), selector.select(array, 1));
        assertEquals(Integer.valueOf(3), selector.select(array, 2));
        assertEquals(Integer.valueOf(4), selector.select(array, 3));
        assertEquals(Integer.valueOf(5), selector.select(array, 4));
    }
}

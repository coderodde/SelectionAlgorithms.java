package com.github.coderodde.algo.selection.impl;

import com.github.coderodde.algo.selection.Selector;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class LinearTimeSelectorTest {
    
    private final Selector<Integer> selector = new LinearTimeSelector<>();
    private Integer[] array ;
    
    @Before
    public void methodInit() {
        this.array = new Integer[]{ 4, 1, 5, 2, 3 };
    }
    
    @Test
    public void testSelect1() {
        assertEquals(Integer.valueOf(1), selector.select(array, 0));
    }
    
    @Test
    public void testSelect2() {
        assertEquals(Integer.valueOf(2), selector.select(array, 1));
    }
    
    @Test
    public void testSelect3() {
        assertEquals(Integer.valueOf(3), selector.select(array, 2));
    }
    
    @Test
    public void testSelect4() {
        assertEquals(Integer.valueOf(4), selector.select(array, 3));
    }
    
    @Test
    public void testSelect5() {
        assertEquals(Integer.valueOf(5), selector.select(array, 4));
    }
    
    @Test
    public void testEqualElements() {
        array = new Integer[]{ 2, 2, 2, 2 };
        
        for (int i = 0; i <= 3; i++) {
            assertEquals(Integer.valueOf(2), selector.select(array, i));
        }
    }
    
    @Test
    public void bruteForceTestUniqueElements() {
        
    }
    
    @Test
    public void bruteForceTestSomeDuplicateElements() {
        
    }
}

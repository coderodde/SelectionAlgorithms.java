package com.github.coderodde.algo.selection;

import static com.github.coderodde.algo.selection.Support.getArray;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class LinearTimeSelectorTest {
    
    private static final int MAXIMUM_ARRAY_CAPACITY = 10_011;
    private static final int MINIMUM_ARRAY_CAPACITY = 5_317;
    
    private static final int MINIMUM_INT = -100_000;
    private static final int MAXIMUM_INT =  100_000;
    
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
    public void testSelect13() {
        Integer[] array = new Integer[13];
        
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        
        for (int i = 0; i < array.length; i++) {
            assertEquals((Integer) i, selector.select(array, i));
        }
    }
    
    @Test
    public void bruteForceTest() {
        Random random = new Random(1L);
        int length =
                MINIMUM_ARRAY_CAPACITY 
                + random.nextInt(
                        MAXIMUM_ARRAY_CAPACITY - MINIMUM_ARRAY_CAPACITY + 1);
        
        int index1 = random.nextInt(length);
        int index2 = random.nextInt(length);
        
        int fromIndex = Math.min(index1, index2);
        int toIndex = Math.max(index1, index2);
        
        Integer[] array1 = getArray(random, 
                                    length,
                                    MINIMUM_INT,
                                    MAXIMUM_INT);
        
        Support.checkArrayContainsNoDuplicatesElements(array1);
        
        Integer[] array2 = array1.clone();
        
        Selector<Integer> sortingSelector = new SortingSelector<>();
        Selector<Integer> linearTimeSelector = new LinearTimeSelector();
        
        for (int i = fromIndex, k = 0; i < toIndex; i++, k++) {
            Integer int1 = 
                    sortingSelector.select(
                            array1, 
                            k, 
                            fromIndex, 
                            toIndex);
            
            Integer int2 = 
                    linearTimeSelector.select(
                            array2, 
                            k, 
                            fromIndex, 
                            toIndex);
            
            assertEquals(int1, int2);
        }
    }
}

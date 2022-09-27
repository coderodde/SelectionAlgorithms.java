package com.github.coderodde.algo.selection;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class RandomizedSelectorTest extends AbstractSelectorTest {

    public RandomizedSelectorTest() {
        super(new RandomizedSelector<>());
    }

    @Test
    public void partition() {
        Integer[] array = { 2, 8, 7, 1, 3, 5, 6, 4 };
        RandomizedSelector.partition(array, 0, array.length);
        assertTrue(Arrays.equals(array, new Integer[] { 
            2, 1, 3, 4, 7, 5, 6, 8
        }));
    }
}

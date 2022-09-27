package com.github.coderodde.algo.selection;

import org.junit.Test;

public class SupportTest {

    @Test(expected = IllegalArgumentException.class)
    public void throwOnDuplicateElementInArray() {
        Integer[] array = { 2, 3, 4, 5, 1, -1, 3 };
        Support.checkArrayContainsNoDuplicatesElements(array);
    }

    @Test
    public void passesOnNoDuplicateElementInArray() {
        Integer[] array = { 2, 3, 4, 5, 1, -1 };
        Support.checkArrayContainsNoDuplicatesElements(array);
    }
}

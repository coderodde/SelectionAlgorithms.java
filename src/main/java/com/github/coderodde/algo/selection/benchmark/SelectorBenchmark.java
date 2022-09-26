package com.github.coderodde.algo.selection.benchmark;

import com.github.coderodde.algo.selection.LinearTimeSelector;
import com.github.coderodde.algo.selection.RandomizedSelector;
import com.github.coderodde.algo.selection.Selector;
import com.github.coderodde.algo.selection.SortingSelector;
import com.github.coderodde.algo.selection.Support;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class implements the selector algorithm benchmark.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Sep 26, 2022)
 * @since 1.6 (Sep 26, 2022)
 */
public final class SelectorBenchmark {
    
    private static final int ARRAY_LENGTH = 10_000;
    private static final int FROM_INDEX = 1000;
    private static final int TO_INDEX = ARRAY_LENGTH - 1000;
    private static final int MINIMUM_VALUE = -100_000;
    private static final int MAXIMUM_VALUE =  100_000;

    public static void main(String[] args) {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        
        System.out.println("seed = " + seed);
        
        Integer[] array1 =
                Support.getArray(
                        random, 
                        ARRAY_LENGTH, 
                        MINIMUM_VALUE, 
                        MAXIMUM_VALUE);
        
        Integer[] array2 = array1.clone();
        Integer[] array3 = array1.clone();
        
        Selector<Integer> selector1 = new SortingSelector<>();
        Selector<Integer> selector2 = new LinearTimeSelector<>();
        Selector<Integer> selector3 = new RandomizedSelector<>();
        
        List<Integer> results1 = warmup(selector1, 
                                        array1,
                                        FROM_INDEX, 
                                        TO_INDEX);
        
        List<Integer> results2 = warmup(selector2, 
                                        array2, 
                                        FROM_INDEX, 
                                        TO_INDEX);
        
        List<Integer> results3 = warmup(selector3, 
                                        array3, 
                                        FROM_INDEX, 
                                        TO_INDEX);
        
        if (results1.equals(results2) && results2.equals(results3)) {
            System.out.println("Algorithms agreed on warmup.");
        }
        
        results1 = benchmark(selector1, array1, FROM_INDEX, TO_INDEX);
        results2 = benchmark(selector2, array2, FROM_INDEX, TO_INDEX);
        results3 = benchmark(selector3, array3, FROM_INDEX, TO_INDEX);
        
        if (results1.equals(results2) && results2.equals(results3)) {
            System.out.println("Algorithms agreed on benchmark.");
        }
    }
    
    private static List<Integer> warmup(Selector<Integer> selector, 
                                        Integer[] array,
                                        int fromIndex,
                                        int toIndex) {
        
        return run(selector, array, fromIndex, toIndex, false);
    }
    
    private static List<Integer> benchmark(Selector<Integer> selector, 
                                           Integer[] array,
                                           int fromIndex,
                                           int toIndex) {
        
        return run(selector, array, fromIndex, toIndex, true);
    }
    
    private static List<Integer> run(Selector<Integer> selector, 
                                     Integer[] array,
                                     int fromIndex,
                                     int toIndex,
                                     boolean print) {
        
        List<Integer> results = new ArrayList<>(toIndex - fromIndex);
        long duration = 0L;
        
        for (int k = 0; k < toIndex - fromIndex; k++) {
            if (selector instanceof SortingSelector) {
                // The selector is a sorting selector. Shuffle the data:
                shuffle(array, fromIndex, toIndex);
            }
            
            long startTime = System.currentTimeMillis();
            
            results.add(selector.select(array, k, fromIndex, toIndex));
            
            long endTime = System.currentTimeMillis();
            duration += endTime - startTime;
        }
        
        
        if (print) {
            System.out.println(
                    selector.getClass().getSimpleName()
                            + " in " 
                            + duration
                            + " milliseconds.");
        }
        
        return results;
    }
    
    private static <E> void shuffle(E[] array, int fromIndex, int toIndex) {
        Random random = new Random();
        List<E> list = new ArrayList<>(toIndex - fromIndex);
        
        for (int i = fromIndex; i < toIndex; i++) {
            list.add(array[i]);
        }
        
        Collections.shuffle(list, random);
        
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = list.get(i - fromIndex);
        }
    }
}

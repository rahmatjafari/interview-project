package com.interview.project;

import com.interview.project.service.PairFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


public class PairFinderTest {

    private PairFinder pairFinder;

    @BeforeEach
    public void setup() {
        pairFinder = new PairFinder();
    }

    @Test
    void findPairs_returnsValidPairs() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int targetNumber = 5;
        Set<List<Integer>> result = pairFinder.findPairs(numbers, targetNumber);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(Arrays.asList(2, 3)));
        Assertions.assertTrue(result.contains(Arrays.asList(1, 4)));
    }

    @Test
    void findPairs_returnsEmptyListForNoPairs() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int targetNumber = 10;
        Set<List<Integer>> result = pairFinder.findPairs(numbers, targetNumber);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void findPairs_returnsUniquePairsWhenDuplicatesExist() {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 5, 0, 0, 5);
        int targetNumber = 5;
        Set<List<Integer>> result = pairFinder.findPairs(numbers, targetNumber);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.contains(Arrays.asList(1, 4)));
        Assertions.assertTrue(result.contains(Arrays.asList(2, 3)));
        Assertions.assertTrue(result.contains(Arrays.asList(0, 5)));
    }

    @Test
    void findPairs_returnsEmptyWhenInputListIsEmpty() {
        List<Integer> numbers = new ArrayList<>();
        int targetNumber = 5;
        Set<List<Integer>> result = pairFinder.findPairs(numbers, targetNumber);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void findPairs_returnsEmptyWhenInputListHasOneNumber() {
        List<Integer> numbers = List.of(5);
        int targetNumber = 5;
        Set<List<Integer>> result = pairFinder.findPairs(numbers, targetNumber);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void findPairs_returnsValidPairsWithNegativeNumbers() {
        List<Integer> numbers = Arrays.asList(-5, -10, 10, 15);
        int targetNumber = 5;
        Set<List<Integer>> result = pairFinder.findPairs(numbers, targetNumber);
        Assertions.assertTrue(result.contains(Arrays.asList(-5, 10)));
        Assertions.assertTrue(result.contains(Arrays.asList(-10, 15)));
        Assertions.assertEquals(2, result.size());
    }
}

package com.interview.project.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PairFinder {

    public Set<List<Integer>> findPairs(List<Integer> numbers, Integer targetNumber) {
        Set<List<Integer>> result = new HashSet<>();
        Set<Integer> seen = new HashSet<>();

        numbers.forEach(num -> {
            int complement = targetNumber - num;
            if (seen.contains(complement)) {
                result.add(Arrays.asList(Math.min(complement, num), Math.max(complement, num)));
            }
            seen.add(num);
        });
        return result;
    }
}

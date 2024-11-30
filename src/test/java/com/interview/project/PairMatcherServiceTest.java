package com.interview.project;

import com.interview.project.dto.PairMatcherRequest;
import com.interview.project.exception.CustomException;
import com.interview.project.exception.CustomExceptionType;
import com.interview.project.service.PairFinder;
import com.interview.project.service.PairMatcherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


@ExtendWith(MockitoExtension.class)
public class PairMatcherServiceTest {

    @Mock
    private PairFinder pairFinder;

    @InjectMocks
    private PairMatcherService pairMatcherService;

    @Test
    void findPairs_returnsValidPairs() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int targetNumber = 5;
        Set<List<Integer>> mockResult = new HashSet<>();
        mockResult.add(Arrays.asList(2, 3));
        mockResult.add(Arrays.asList(1, 4));
        Mockito.when(pairFinder.findPairs(numbers, targetNumber)).thenReturn(mockResult);
        Set<List<Integer>> result = pairMatcherService.findPairs(new PairMatcherRequest(numbers, targetNumber));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(Arrays.asList(2, 3)));
        Assertions.assertTrue(result.contains(Arrays.asList(1, 4)));
    }

    @Test
    void findPairs_throwsExceptionForNoMatchingPairs() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int targetNumber = 10;
        Mockito.when(pairFinder.findPairs(numbers, targetNumber)).thenReturn(new HashSet<>());
        PairMatcherRequest request = new PairMatcherRequest(numbers, targetNumber);
        CustomException exception = Assertions.assertThrows(
                CustomException.class,
                () -> pairMatcherService.findPairs(request)
        );
        Assertions.assertEquals(CustomExceptionType.PAIRS_NOT_FOUND, exception.getExceptionType());
    }

}

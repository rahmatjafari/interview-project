package com.interview.project.service;

import com.interview.project.dto.PairMatcherRequest;
import com.interview.project.exception.CustomException;
import com.interview.project.exception.CustomExceptionType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PairMatcherService {

    private final PairFinder pairFinder;

    public PairMatcherService(PairFinder pairFinder) {
        this.pairFinder = pairFinder;
    }

    public Set<List<Integer>> findPairs(PairMatcherRequest request) {
        Set<List<Integer>> result = pairFinder.findPairs(request.getNumbers(), request.getTargetNumber());
        if (result.isEmpty()) {
            throw new CustomException(CustomExceptionType.PAIRS_NOT_FOUND);
        }
        return result;
    }
}

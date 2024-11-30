package com.interview.project.controller;

import com.interview.project.dto.PairMatcherRequest;
import com.interview.project.service.PairMatcherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pairs")
@Validated
public class PairMatcherController {
    private final PairMatcherService pairMatcherService;

    public PairMatcherController(PairMatcherService pairMatcherService) {
        this.pairMatcherService = pairMatcherService;
    }

    @PostMapping("/find")
    public ResponseEntity<?> findPairs(@RequestBody @Valid PairMatcherRequest request) {
        Set<List<Integer>> pairs = pairMatcherService.findPairs(request);
        return ResponseEntity.ok(pairs);
    }
}

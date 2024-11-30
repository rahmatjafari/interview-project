package com.interview.project.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PairMatcherRequest {
    @NotNull
    @Size(min = 2, max = 1000)
    private List<Integer> numbers;
    @NotNull
    private Integer targetNumber;
}

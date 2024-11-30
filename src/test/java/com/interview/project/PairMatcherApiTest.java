package com.interview.project;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PairMatcherApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findPairs_respondsOkWithValidPairs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/pairs/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numbers\":[1,2,3,4,5],\"targetNumber\":5}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.containsInAnyOrder(
                        Matchers.contains(2, 3),
                        Matchers.contains(1, 4)
                )));
    }

    @Test
    void findPairs_respondsBadRequestForNullNumbers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/pairs/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"targetNumber\":5}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void findPairs_respondsBadRequestForEmptyNumbers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/pairs/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numbers\":[],\"targetNumber\":5}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void findPairs_respondsBadRequestForSingleNumberInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/pairs/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numbers\":[5],\"targetNumber\":5}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void findPairs_respondsBadRequestForNullTargetNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/pairs/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numbers\":[5,6]}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void findPairs_respondsNotFoundForNoMatchingPairs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/pairs/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numbers\":[1,2,3,4,5],\"targetNumber\":10}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

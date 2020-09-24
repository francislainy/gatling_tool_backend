package com.francislainy.gatling_tool.controller.category;

import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.model.entity.category.Category;
import com.francislainy.gatling_tool.model.entity.report.Report;
import com.francislainy.gatling_tool.repository.category.CategoryRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CategoryRepository categoryRepository;

    @Before
    public void setupAll() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCategory() throws Exception {

        Category category = new Category();
        category.setId(UUID.fromString("fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e"));
        category.setTitle("My another category");
        when(categoryRepository.findById(UUID.fromString("fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e"))).thenReturn(java.util.Optional.of(category));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/gatling-tool/category/fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("\n" +
                        "{\"id\":\"fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e\",\"title\":\"My another category\",\"reports\":null}"))
                .andReturn();

    }


    @Test
    public void listAllCategories() throws Exception {

        Category category = new Category();
        category.setId(UUID.fromString("fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e"));
        category.setTitle("My another category");

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/gatling-tool/category/")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andReturn();

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"categories\":[{\"id\":\"fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e\",\"title\":\"My another category\",\"reports\":null}]}"))
                .andReturn();

    }


    @Test
    public void getReportByCategory() throws Exception {

        CategoryQueryDto category = new CategoryQueryDto();
        category.setId(UUID.fromString("fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e"));
        category.setTitle("My another category");
        Report report = new Report(UUID.fromString("d4bc078a-2a46-4233-b9db-b1e5ff0f83d2"), "My saturday report", "today", "today", null);
        ArrayList reports = new ArrayList();
        reports.add(report);
        category.setReports(reports);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/gatling-tool/category/92d1aa1f-6d2d-44e1-a698-a5b2e1c19b96/include-reports")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andReturn();

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(""))
                .andReturn();
    }


}
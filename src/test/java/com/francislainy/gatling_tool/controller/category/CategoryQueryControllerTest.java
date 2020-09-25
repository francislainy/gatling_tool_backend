package com.francislainy.gatling_tool.controller.category;

import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.model.entity.category.Category;
import com.francislainy.gatling_tool.model.entity.report.Report;
import com.francislainy.gatling_tool.repository.category.CategoryRepository;
import com.francislainy.gatling_tool.repository.report.ReportRepository;
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

    @MockBean
    ReportRepository reportRepository;

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
        Category category = new Category();
        category.setId(UUID.fromString("fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e"));
        category.setTitle("My another category");
        when(categoryRepository.findById(UUID.fromString("fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e"))).thenReturn(java.util.Optional.of(category));
        Report report = new Report(UUID.fromString("d4bc078a-2a46-4233-b9db-b1e5ff0f83d2"), "My saturday report", "today", "today", category);
        ArrayList reports = new ArrayList();
        reports.add(report);
        when(reportRepository.findByCategory_Id(category.getId())).thenReturn(reports);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/gatling-tool/category/fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e/include-reports")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andReturn();

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"category\":{\"id\":\"fdbfb1ec-1f1e-4867-9cc8-73929fbcc07e\"," +
                        "\"title\":\"My another category\",\"reports\":[{\"id\":\"d4bc078a-2a46-4233-b9db-b1e5ff0f83d2\"," +
                        "\"title\":\"My saturday report\",\"runDate\":\"today\",\"createdDate\":\"today\",\"category\":null}]}}"))
                .andReturn();
    }


}
package com.francislainy.gatling_tool.controller.category;

import com.francislainy.gatling_tool.dto.category.CategoryQueryDto;
import com.francislainy.gatling_tool.model.entity.category.Category;
import com.francislainy.gatling_tool.repository.category.CategoryRepository;
import com.francislainy.gatling_tool.service.category.CategoryQueryService;
import com.francislainy.gatling_tool.service.impl.category.CategoryQueryServiceImpl;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(CategoryQueryController.class)
class CategoryQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryQueryServiceImpl categoryQueryServiceImpl;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setupAll(){
        MockitoAnnotations.initMocks(this);
    }


    @BeforeEach
    public void setup(){
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


//        String responseBody = result.getResponse().getContentAsString();
//        ResponseDto responseDto
//                = new Gson().fromJson(responseBody, ResponseDto.class);
//        Assertions.assertThat(responseDto.id).isEqualTo("hello world!");

//        System.out.println(result);
    }


    @Test
    public void listAllCategories() throws Exception {



        when(categoryRepository.findAll()).thenReturn(null);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/gatling-tool/category/")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andReturn();

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{categories:[{report:dio}]}"))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ResponseDto responseDto
                = new Gson().fromJson(responseBody, ResponseDto.class);
//        Assertions.assertThat(responseDto.id).isEqualTo("hello world!");

        System.out.println(result);
    }


    @Test
    public void getReportByCategory() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/gatling-tool/category/92d1aa1f-6d2d-44e1-a698-a5b2e1c19b96/include-reports")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andReturn();

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }


}
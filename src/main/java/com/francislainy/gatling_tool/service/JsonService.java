package com.francislainy.gatling_tool.service;

import com.francislainy.gatling_tool.helper.JsonHelper;
import com.francislainy.gatling_tool.model.entity.JsonTutorial;
import com.francislainy.gatling_tool.repository.JsonTutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class JsonService {

    @Autowired
    JsonTutorialRepository repository;

    public void save(MultipartFile file) {
        try {
            List<JsonTutorial> tutorials = JsonHelper.jsonToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<JsonTutorial> tutorials = repository.findAll();

        ByteArrayInputStream in = JsonHelper.tutorialsToJson(tutorials);
        return in;
    }

    public List<JsonTutorial> getAllTutorials() {
        return repository.findAll();
    }
}

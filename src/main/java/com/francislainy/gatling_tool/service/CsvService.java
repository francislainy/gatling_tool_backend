package com.francislainy.gatling_tool.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.francislainy.gatling_tool.model.entity.Tutorial;
import com.francislainy.gatling_tool.repository.TutorialRepository;
import com.francislainy.gatling_tool.helper.CsvHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvService {

    @Autowired
    TutorialRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Tutorial> tutorials = CsvHelper.csvToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<Tutorial> tutorials = repository.findAll();

        ByteArrayInputStream in = CsvHelper.tutorialsToCSV(tutorials);
        return in;
    }

    public List<Tutorial> getAllTutorials() {
        return repository.findAll();
    }
}

package com.francislainy.gatling_tool.service;

import com.francislainy.gatling_tool.helper.HtmlHelper;
import com.francislainy.gatling_tool.model.entity.HtmlTutorial;
import com.francislainy.gatling_tool.repository.HtmlTutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class HtmlService {

    @Autowired
    HtmlTutorialRepository repository;

    public void save(MultipartFile file) {
        List<HtmlTutorial> tutorials = HtmlHelper.htmlToTutorials(file);
        repository.saveAll(tutorials);
    }
}

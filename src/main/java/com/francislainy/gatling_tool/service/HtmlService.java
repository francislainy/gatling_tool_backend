package com.francislainy.gatling_tool.service;

import com.francislainy.gatling_tool.helper.HtmlHelper;
import com.francislainy.gatling_tool.repository.HtmlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HtmlService {

    @Autowired
    HtmlRepository repository;

    public int save(MultipartFile file) {
        Integer numUsers = HtmlHelper.htmlToTutorials(file);
        return numUsers;

//        StatsEntity statsEntity = new StatsEntity();

//        repository.save(statsEntity);
    }
}

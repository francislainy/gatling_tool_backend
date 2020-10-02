package com.francislainy.gatling_tool.service;

import com.francislainy.gatling_tool.helper.StatsJsonHelper;
import com.francislainy.gatling_tool.model.entity.StatsEntity;
import com.francislainy.gatling_tool.repository.StatsJsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class StatsJsonService {

    @Autowired
    StatsJsonRepository repository;

    public void save(MultipartFile file, UUID id) {
        StatsEntity statsEntity = StatsJsonHelper.jsonToStats(file, id);
        repository.save(statsEntity);
    }



//    public ByteArrayInputStream load() {
//        List<Stats> tutorials = repository.findAll();
//
//        ByteArrayInputStream in = StatsJsonHelper.tutorialsToJson(tutorials);
//        return in;
//    }

    public List<StatsEntity> getAllTutorials() {
        return repository.findAll();
    }
}

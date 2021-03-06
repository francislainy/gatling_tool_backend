package com.francislainy.gatling_tool.service.stats;

import com.francislainy.gatling_tool.helper.StatsJsonHelper;
import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import com.francislainy.gatling_tool.repository.stats.StatsJsonRepository;
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
        List<StatsEntity> statsEntityList = StatsJsonHelper.jsonToStatsList(file, id);

        for (StatsEntity statsEntity : statsEntityList) {
            repository.save(statsEntity);
        }

    }

}

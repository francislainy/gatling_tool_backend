package com.francislainy.gatling_tool.service.impl.stats;

import com.francislainy.gatling_tool.dto.stats.Stats;
import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import com.francislainy.gatling_tool.repository.stats.StatsRepository;
import com.francislainy.gatling_tool.service.stats.StatsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StatsQueryServiceImpl implements StatsQueryService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public Stats getStats(UUID id) {

        if (statsRepository.findById(id).isPresent()) {
            StatsEntity statsEntity = statsRepository.findById(id).get();
            return new Stats(statsEntity.getName());
        } else {
            return null;
        }
    }

    @Override
    public List<Stats> listAllStats() {

        List<Stats> statsList = new ArrayList<>();

        statsRepository.findAll().forEach(statsEntity -> {
            statsList.add(new Stats(statsEntity.getName()));
        });

        return statsList;
    }
}

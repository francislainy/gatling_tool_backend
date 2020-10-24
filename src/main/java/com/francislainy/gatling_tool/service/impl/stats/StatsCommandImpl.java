package com.francislainy.gatling_tool.service.impl.stats;

import com.francislainy.gatling_tool.dto.stats.Stats;
import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import com.francislainy.gatling_tool.repository.stats.StatsRepository;
import com.francislainy.gatling_tool.service.stats.StatsCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StatsCommandImpl implements StatsCommandService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public Stats updateStats(UUID id, Stats statsUpdateDto) {
        return null;
    }

    @Override
    public void deleteStats(UUID id) {

        if (statsRepository.findById(id).isPresent()) {

            StatsEntity existingReport = statsRepository.findById(id).get();

            statsRepository.delete(existingReport);

        }
    }
}

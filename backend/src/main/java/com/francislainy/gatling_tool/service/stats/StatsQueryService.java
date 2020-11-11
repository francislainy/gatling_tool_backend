package com.francislainy.gatling_tool.service.stats;

import com.francislainy.gatling_tool.dto.stats.Stats;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface StatsQueryService {

    Stats getStats(UUID id);

    List<Stats> listAllStats();

    List<Stats> listAllStatsByReport(UUID id);

}

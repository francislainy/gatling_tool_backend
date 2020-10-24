package com.francislainy.gatling_tool.service.stats;

import com.francislainy.gatling_tool.dto.stats.Stats;

import java.util.UUID;

public interface StatsCommandService {

    Stats updateStats(UUID id, Stats statsUpdateDto);

    void deleteStats(UUID id);
}

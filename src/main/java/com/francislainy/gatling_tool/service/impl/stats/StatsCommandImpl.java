package com.francislainy.gatling_tool.service.impl.stats;

import com.francislainy.gatling_tool.dto.stats.Stats;
import com.francislainy.gatling_tool.model.entity.report.Report;
import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import com.francislainy.gatling_tool.repository.report.ReportRepository;
import com.francislainy.gatling_tool.repository.stats.StatsRepository;
import com.francislainy.gatling_tool.service.stats.StatsCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StatsCommandImpl implements StatsCommandService {

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Stats updateStats(UUID id, Stats statsUpdateDto) {

        if (statsRepository.findById(id).isPresent()) {

            StatsEntity existingStats = statsRepository.findById(id).get();
            existingStats.setName(statsUpdateDto.getName());

            Report existingReport = reportRepository.findById(statsUpdateDto.getReportId()).get();
            Report report = existingReport;
            report.setId(statsUpdateDto.getReportId());
            existingStats.setReportId(report.getId()); // This is needed to remove hibernate interceptor to be set together with the other category properties

            StatsEntity updatedStats = statsRepository.save(existingStats);
            updatedStats.setReportId(existingReport.getId());

            Stats stats = new Stats(updatedStats.getId(), updatedStats.getReportId(), updatedStats.getName());

            return stats;

        } else {
            return null;
        }
    }

    @Override
    public void deleteStats(UUID id) {

        if (statsRepository.findById(id).isPresent()) {

            StatsEntity existingReport = statsRepository.findById(id).get();

            statsRepository.delete(existingReport);

        }
    }
}

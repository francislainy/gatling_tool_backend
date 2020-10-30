package com.francislainy.gatling_tool.model.entity.report;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.francislainy.gatling_tool.model.entity.category.Category;
import com.francislainy.gatling_tool.model.entity.stats.StatsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report")
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "report_title", nullable = false)
    private String reportTitle;
    @Column(name = "run_date", nullable = false)
    private Long run_date;
    @Column(name = "created_date", nullable = false)
    private Long created_date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Report(UUID id) {
        this.id = id;
    }


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<StatsEntity> statsEntity;


    public Report(UUID id, String title) {

        this.id = id;
        this.reportTitle = title;
    }


    public void addStats(StatsEntity s) {
        if (this.statsEntity == null) {
            this.statsEntity = new ArrayList<>();
        }
        s.setReport(this);
        this.statsEntity.add(s);
    }


    public void removeStats(StatsEntity s) {
        if (this.statsEntity != null) {
            s.setReport(null);
            this.statsEntity.remove(s);
        }
    }

}

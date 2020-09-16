package com.francislainy.gatling_tool.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "report")
@Data
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "report_title", nullable = false)
    private String reportTitle;
    @Column(name = "run_date", nullable = false)
    private String run_date;
    @Column(name = "created_date", nullable = false)
    private String created_date;
    @Column(name = "category_title", nullable = false)
    private String categoryTitle;

}

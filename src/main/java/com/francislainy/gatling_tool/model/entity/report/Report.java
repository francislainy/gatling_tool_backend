package com.francislainy.gatling_tool.model.entity.report;

import com.francislainy.gatling_tool.model.entity.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @JoinColumn(name = "category_id",  nullable = false)
    private Category category;

}

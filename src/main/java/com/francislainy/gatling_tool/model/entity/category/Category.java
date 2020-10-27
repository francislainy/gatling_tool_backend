package com.francislainy.gatling_tool.model.entity.category;

import com.francislainy.gatling_tool.model.entity.report.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "category_title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Collection<Report> report;

    public Category(UUID id, String title) {

        this.id = id;
        this.title = title;
    }

    public void addReport(Report r){
        if (this.report == null){
            this.report = new ArrayList<>();
        }
        this.report.add(r);
    }
}
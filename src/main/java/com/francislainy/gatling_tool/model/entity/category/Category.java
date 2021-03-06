package com.francislainy.gatling_tool.model.entity.category;

import com.francislainy.gatling_tool.model.entity.report.Report;
import lombok.*;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Report> reports;

    public Category(UUID id, String title) {

        this.id = id;
        this.title = title;
    }

    public void addReport(Report r) {
        if (this.reports == null) {
            this.reports = new ArrayList<>();
        }
        r.setCategory(this);
        this.reports.add(r);
    }

    public void removeReport(Report r) {
        if (this.reports != null) {
            r.setCategory(null);
            this.reports.remove(r);
        }
    }

}
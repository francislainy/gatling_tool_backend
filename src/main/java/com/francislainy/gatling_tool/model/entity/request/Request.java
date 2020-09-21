package com.francislainy.gatling_tool.model.entity.request;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "request")
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "request_title", nullable = false)
    private String title;

}
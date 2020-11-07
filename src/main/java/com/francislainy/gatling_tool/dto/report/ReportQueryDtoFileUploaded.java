package com.francislainy.gatling_tool.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportQueryDtoFileUploaded {

    private UUID id;

    private Integer numberOfUsers;
    private Long duration;

}

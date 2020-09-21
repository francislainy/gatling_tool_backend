package com.francislainy.gatling_tool.repository.request;

import com.francislainy.gatling_tool.model.entity.request.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RequestRepository extends CrudRepository<Request, UUID> {

}

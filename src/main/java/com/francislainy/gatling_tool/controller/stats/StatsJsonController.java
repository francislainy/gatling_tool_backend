package com.francislainy.gatling_tool.controller.stats;

import com.francislainy.gatling_tool.helper.StatsJsonHelper;
import com.francislainy.gatling_tool.message.ResponseMessage;
import com.francislainy.gatling_tool.service.stats.StatsJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@CrossOrigin()
@Controller
@RequestMapping("/api/gatling-tool/json")
public class StatsJsonController {

    @Autowired
    StatsJsonService fileService;

    @PostMapping(value = "/import/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") UUID id) {
        String message = "";

        if (StatsJsonHelper.hasJsonFormat(file)) {
            try {
                fileService.save(file, id);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a json file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

}

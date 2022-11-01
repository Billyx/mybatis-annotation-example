package com.requirement.api.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class Requirement {

    private Integer id;
    private long idWorkerApplicant;
    private String description;
    private String attachedFile;
}

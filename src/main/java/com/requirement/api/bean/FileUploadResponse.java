package com.requirement.api.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadResponse {
    private String filename;
    private String downloadUri;
    private long size;
}

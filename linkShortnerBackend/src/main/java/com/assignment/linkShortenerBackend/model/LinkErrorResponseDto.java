package com.assignment.linkShortenerBackend.model;

import lombok.Data;

@Data
public class LinkErrorResponseDto {

    private String status;
    private String error;
}

package com.assignment.linkShortenerBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LinkResponseDto {

    private String originalUrl;
    private String shortLink;
    private LocalDateTime expirationDate;
}

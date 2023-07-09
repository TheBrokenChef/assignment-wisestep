package com.assignment.linkShortenerBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Link {

    @Id
    @GeneratedValue
    private long id;
    private String originalLink;
    private String shortLink;
    private LocalDateTime creationTime;
    private LocalDateTime expirationTime;
}

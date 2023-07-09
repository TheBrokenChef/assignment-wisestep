package com.assignment.linkShortenerBackend.repository;

import com.assignment.linkShortenerBackend.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    @Query("SELECT l FROM Link l WHERE l.shortLink LIKE %:shortLink%")
    public Link findByShortLinkLike(String shortLink);
}

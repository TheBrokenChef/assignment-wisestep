package com.assignment.linkShortenerBackend.service;

import com.assignment.linkShortenerBackend.model.Link;
import com.assignment.linkShortenerBackend.repository.LinkRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class LinkShortenerService {

    @Autowired
    private LinkRepository linkRepository;

    public Link generateShortLink(String originalLink){
        if(originalLink.isEmpty()){
            return null;
        }
        LocalDateTime time = LocalDateTime.now();
        String encodedLink = Hashing.adler32()
                .hashString(originalLink.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        String headerLink = "http://localhost:8080/";
        Link link = new Link();
        link.setOriginalLink(originalLink);
        link.setShortLink(headerLink + encodedLink);
        link.setCreationTime(LocalDateTime.now());
        link.setExpirationTime(LocalDateTime.now().plusMinutes(5));

        Link savedLink = linkRepository.save(link);

        return savedLink;
    }

    public Link getEncodedUrl(String shortLink){
        Link link = linkRepository.findByShortLinkLike(shortLink);
        return link;
    }

    public void deleteShortLink(Link link) {
        linkRepository.delete(link);
    }
}

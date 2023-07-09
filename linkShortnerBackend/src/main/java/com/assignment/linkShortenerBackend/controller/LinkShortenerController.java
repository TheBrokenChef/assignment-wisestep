package com.assignment.linkShortenerBackend.controller;

import com.assignment.linkShortenerBackend.model.Link;
import com.assignment.linkShortenerBackend.model.LinkErrorResponseDto;
import com.assignment.linkShortenerBackend.model.LinkResponseDto;
import com.assignment.linkShortenerBackend.service.LinkShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@CrossOrigin("http://localhost:3000")
public class LinkShortenerController {

    @Autowired
    private LinkShortenerService linkShortenerService;

    @GetMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestParam("link") String inputLink){

        Link link = linkShortenerService.generateShortLink(inputLink);

        if(link != null) {
            LinkResponseDto linkResponseDto = new LinkResponseDto();
            linkResponseDto.setShortLink(link.getShortLink());
            linkResponseDto.setOriginalUrl(link.getOriginalLink());
            linkResponseDto.setExpirationDate(link.getExpirationTime());

            return new ResponseEntity<LinkResponseDto>(linkResponseDto, HttpStatus.OK);
        }

        LinkErrorResponseDto linkErrorResponseDto = new LinkErrorResponseDto();
        linkErrorResponseDto.setStatus("404");
        linkErrorResponseDto.setError("Please Enter a valid link for shortening");

        return new ResponseEntity<LinkErrorResponseDto>(linkErrorResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginalLink(@PathVariable String shortLink, HttpServletResponse response) throws IOException {

        if(shortLink.isEmpty()){
            LinkErrorResponseDto linkErrorResponseDto = new LinkErrorResponseDto();
            linkErrorResponseDto.setError("Invalid Url");
            linkErrorResponseDto.setStatus("400");
            return new ResponseEntity<LinkErrorResponseDto>(linkErrorResponseDto,HttpStatus.OK);
        }

        Link link = linkShortenerService.getEncodedUrl(shortLink);

        if(link == null) {
            LinkErrorResponseDto linkErrorResponseDto = new LinkErrorResponseDto();
            linkErrorResponseDto.setError("Url does not exist. Please try generating a fresh one.");
            linkErrorResponseDto.setStatus("400");
            return new ResponseEntity<LinkErrorResponseDto>(linkErrorResponseDto,HttpStatus.OK);
        } else if(link.getExpirationTime().isBefore(LocalDateTime.now())){
            linkShortenerService.deleteShortLink(link);
            LinkErrorResponseDto linkErrorResponseDto = new LinkErrorResponseDto();
            linkErrorResponseDto.setError("Url Expired. Please try generating a fresh one.");
            linkErrorResponseDto.setStatus("200");
            return new ResponseEntity<LinkErrorResponseDto>(linkErrorResponseDto,HttpStatus.OK);
        }

        response.sendRedirect(link.getOriginalLink());
        return null;
    }
}

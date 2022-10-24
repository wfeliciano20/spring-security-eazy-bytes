package com.williamfeliciano.springsecurityeasybytes.controllers;

import com.williamfeliciano.springsecurityeasybytes.models.Notice;
import com.williamfeliciano.springsecurityeasybytes.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticesController {

    private final NoticeRepository noticeRepository;
    @Autowired
    public NoticesController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {

        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if(notices != null){
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        }else{
            return null;
        }
    }

}
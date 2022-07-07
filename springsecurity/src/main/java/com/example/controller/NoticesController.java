package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Notice;
import com.example.repository.NoticeRepository;

/**
 * 
 * @author kadam.sachin
 *
 */
@RestController
public class NoticesController {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@GetMapping("/notices")
	public List<Notice> getNotices() {
		List<Notice> notices = noticeRepository.findAllActiveNotices();
		if (notices != null ) {
			return notices;
		}else {
			return null;
		}
	}
}

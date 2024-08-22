package com.springboot.angular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.angular.model.Feedback;
import com.springboot.angular.service.impl.FeedbackService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/save_feedback")
	public Feedback saveFeedback(@RequestBody Feedback feedback) {
		return feedbackService.saveFeedback(feedback);
	}
	@GetMapping("/get_all_feedbacks")
	public List<Feedback> getAllFeedbacks(){
		return feedbackService.getAllFeedbacks();
	}
}
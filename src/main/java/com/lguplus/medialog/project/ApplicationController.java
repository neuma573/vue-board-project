package com.lguplus.medialog.project;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController

public class ApplicationController {


	@RequestMapping("/")
    public String home() {
		return "redirect:/view/home";
    }

	@GetMapping("login")
	public String login() {
		return "loginView.vue";
	}
	
	@GetMapping("/api/public/health")
	public boolean health() {
		return true;
	}
	
	@GetMapping("/favicon.ico")
	public String favicon() {
		return "forward:/static/favicon.ico";
	}
	
	@GetMapping("/ServerDataTest")
	public String ServerData() {
		
	    return "나호진";
	}

}

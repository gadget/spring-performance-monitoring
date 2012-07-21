package com.greenformatics.spring.demo.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenformatics.spring.demo.beans.SomeService;

@Controller
public class HomeController {

	@Inject
	private SomeService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() throws InterruptedException {
		service.loadSomething();

		return "home";
	}

}

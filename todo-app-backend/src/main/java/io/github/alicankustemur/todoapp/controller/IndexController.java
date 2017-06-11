package io.github.alicankustemur.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.github.alicankustemur.todoapp.service.CreateTestDataService;

@Controller
public class IndexController {
	
	@Autowired
	private CreateTestDataService createTestData;
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView modelAndView) {
		createTestData.create();
		modelAndView.setViewName("index");
		return modelAndView;
	}

}

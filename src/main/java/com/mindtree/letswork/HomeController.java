package com.mindtree.letswork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.mindtree.letswork.property.LetsWorkProperty;

@Controller
@CrossOrigin
public class HomeController {
	@Autowired
	private LetsWorkProperty letsWorkProperty; 
	
	
	@GetMapping("/")
	public String home(ModelMap model) {
		model.addAttribute("work", letsWorkProperty);
		return "letsworkindex";
	}
}

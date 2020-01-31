package com.PInc.PBank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PInc.PBank.model.Person;
import com.PInc.PBank.service.PersonService;

@Controller
public class ProfileController {
	
	private final PersonService personService;
	
	@Autowired
	public ProfileController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping("/profile")
	public String profile(@RequestParam("name") String name, Model model) {
		Person p = personService.getPersonByName(name).orElse(null);
		model.addAttribute("id", p.getId());
		model.addAttribute("name", p.getName());
		return "profile";
	}
}

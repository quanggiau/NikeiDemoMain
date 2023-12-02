package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.model.Content;
import net.javaguides.springboot.service.ContentService;

//@RestController
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	@GetMapping("/all")
	public String getall(Model model) {
		List<Content> content = contentService.getAllContents();
		model.addAttribute("ListContent", content);
		//return "redirect:/";
		return "index";
	}

}
package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.model.Content;
import net.javaguides.springboot.service.ContentService;

//@RestController
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	// @GetMapping("/all")
	// public ResponseEntity getall() {
	// 	return content = contentService.getAllContents();

	// }




	// @GetMapping("/")
	// public String viewHomePage(Model model) {
	// 	return "redirect:/content/all";
	// }
	

	// @GetMapping("/all")
	// public String getall(Model model) {
	// 	List<Content> content = contentService.getAllContents();
	// 	model.addAttribute("ListContent", content);
	// 	//return "redirect:/";
	// 	return "index_detail";
	// }

	// @GetMapping("/showNewContentForm")
	// public String showNewContentForm(Model model) {
	// 	// create model attribute to bind form data
	// 	Content content = new Content();
	// 	model.addAttribute("content", content);
	// 	return "new_content";
	// }

	// @PostMapping("/saveContent")
	// public ResponseEntity saveContent(@ModelAttribute("content") Content content) {
	// 	// save employee to database
	// 	contentService.saveContent(content);
	// 	return "redirect:/content/all";
	// }

}
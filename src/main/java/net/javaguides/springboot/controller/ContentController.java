package net.javaguides.springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Content;
import net.javaguides.springboot.repository.ServiceMasterRepository;
import net.javaguides.springboot.service.ContentService;
import net.javaguides.springboot.service.ServiceMasterSRV;

//@RestController
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	@Autowired
	private ServiceMasterSRV serviceMTService;

	@Autowired
	private ServiceMasterRepository repoMT;

	
	@GetMapping("/showNewContentForm")
	public String showNewContentForm(Model model) {
		// create model attribute to bind form data
		Content content = new Content();
		model.addAttribute("content", content);
		model.addAttribute("serviceMasterList", serviceMTService.getAllService());
		return "new_content";
	}

	// @PostMapping("/saveContent")
	@RequestMapping(value="/saveContent", method=RequestMethod.POST, params="saveContent")
	public String saveContent(@ModelAttribute("content") Content content) {
		// save content to database
		String ct = content.getQuestion();
		String[] splCT = ct.split(",");
		String[] splAn = content.getAnswer().split(",");
		for(int i = 0; i<=splCT.length-1; i++)
		{
			Content conDB = new Content();
			conDB.setAnswer(splAn[i]);
			conDB.setIdService(content.getIdService());
			conDB.setQuestion(splCT[i]);
			contentService.saveContent(conDB);
		}
		
		return "redirect:/servicemaster/all";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam long id,Model model) {
		// create model attribute to bind form data
		List<Content> contentDContents = contentService.getContentByIdService(id);
		model.addAttribute("content", model);
		model.addAttribute("contentItem", contentDContents);
		model.addAttribute("serviceMT", repoMT.getReferenceById(id) );
		return "update_content";
	}

		@PostMapping("/editContent")
		public String editContent(@ModelAttribute Content content) {
		// save content to database
		contentService.updateContent(content);
		
		return "redirect:/servicemaster/all";
	}

	
	@RequestMapping(value="/saveContent", method=RequestMethod.POST, params="saveAndcallGPT")
	public String saveAndcallGPT(@ModelAttribute("content") Content content) {
		// 1. save content to database
		String ct = content.getQuestion();
		String[] splCT = ct.split(",");
		String[] splAn = content.getAnswer().split(",");
		for(int i = 0; i<=splCT.length-1; i++)
		{
			Content conDB = new Content();
			conDB.setAnswer(splAn[i]);
			conDB.setIdService(content.getIdService());
			conDB.setQuestion(splCT[i]);
			contentService.saveContent(conDB);
		}

		// 2. call API
		
		return "redirect:/servicemaster/all";
 	}

}
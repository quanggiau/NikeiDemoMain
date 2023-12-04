package net.javaguides.springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Content;
import net.javaguides.springboot.model.Employee;
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

	@PostMapping("/saveContent")
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

	// @GetMapping("/showFormForUpdate")
	// public String showFormForUpdate(@RequestParam long id, Model model) {

	// 	List<Content> content = contentService.getContentByIdService(id);
	// 	model.addAttribute("content", content);

	// 	//model.addAttribute("serviceMasterList", serviceMTService.getAllService());
		
	// 	// get employee from the service
	// 	// Employee employee = employeeService.getEmployeeById(id);
		
	// 	// // set employee as a model attribute to pre-populate the form
	// 	// model.addAttribute("employee", employee);
	// 	return "update_content";
	// }

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam long id,Model model) {
		// create model attribute to bind form data
		List<Content> contentDContents = contentService.getContentByIdService(id);
		model.addAttribute("content", model);
		model.addAttribute("contentItem", contentDContents);
		model.addAttribute("serviceMT", repoMT.getReferenceById(id) );
		return "update_content";
	}

}
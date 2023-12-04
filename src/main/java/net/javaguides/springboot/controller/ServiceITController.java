package net.javaguides.springboot.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.javaguides.springboot.model.ServiceIT;
import net.javaguides.springboot.service.ServiceITService;

//@RestController
@Controller
@RequestMapping("/service")
public class ServiceITController {
    @Autowired
	private ServiceITService serviceITService;

    @GetMapping("/all")
	public String serviceList(Model model) {
		model.addAttribute("serviceList", serviceITService.getAllServices());
        return "index";
	}



    // @GetMapping("/newservice")
	// public String showNewServiceForm(Model model) {
	// 	// create model attribute to bind form data
	// 	ServiceIT service = new ServiceIT();
	// 	model.addAttribute("content", service);
	// 	return "new_service";
	// }

    // @PostMapping("/saveService")
	// public String saveService(@ModelAttribute("service") ServiceIT service) {
	// 	// save employee to database
    //     service.setCreateBy("demo");
    //     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //     Date date = new Date();
    //     service.setCreateAt(String.valueOf(dateFormat.format(date)));
	// 	serviceITService.saveService(service);
	// 	return "redirect:/service/";
	// }

}

package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.service.ServiceMasterSRV;

@Controller
@RequestMapping("/servicemaster")
public class ServiceMasterController {
    @Autowired
	private ServiceMasterSRV serviceMTService;

    @GetMapping("/all")
	public String serviceList(Model model) {
		model.addAttribute("serviceMasterList", serviceMTService.getAllService());
        return "index";
	}
    
}
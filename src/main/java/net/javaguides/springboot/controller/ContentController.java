package net.javaguides.springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import net.javaguides.springboot.dto.gpt.ChatRequest;
import net.javaguides.springboot.dto.gpt.ChatResponse;
import net.javaguides.springboot.dto.gpt.DataFromAjax;
import net.javaguides.springboot.dto.gpt.Message;
import net.javaguides.springboot.model.Content;
import net.javaguides.springboot.repository.ServiceMasterRepository;
import net.javaguides.springboot.service.ContentService;
import net.javaguides.springboot.service.ServiceMasterSRV;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;

	@Autowired
	private ServiceMasterSRV serviceMTService;

	@Autowired
	private ServiceMasterRepository repoMT;

	@Autowired
  private RestTemplate restTemplate;

	@Value("${openai.chatgtp.model}")
	private String modelGPT;

	@Value("${openai.chatgtp.max-completions}")
	private int maxCompletions;

	@Value("${openai.chatgtp.temperature}")
	private double temperature;

	@Value("${openai.chatgtp.max_tokens}")
	private int maxTokens;

	@Value("${openai.chatgtp.api.url}")
	private String apiUrl;

	@Value("${openai.chatgtp.api.stop}")
	private String stop;

	
	@GetMapping("/showNewContentForm")
	public String showNewContentForm(Model model) {
		// create model attribute to bind form data
		Content content = new Content();
		model.addAttribute("content", content);
		model.addAttribute("serviceMasterList", serviceMTService.getAllService());
		return "new_content";
	}

	// Update content
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
	public String showFormForUpdate(@RequestParam long id, Model model) {
		// create model attribute to bind form data
		List<Content> contentDContents = contentService.getContentByIdService(id);
		model.addAttribute("content", model);
		model.addAttribute("contentItem", contentDContents);
		model.addAttribute("serviceMT", repoMT.getReferenceById(id) );
		return "update_content";
	}

		// Update form
		@PostMapping("/editContent")
		public String editContent(@RequestBody DataFromAjax dt) {
			for(int i = 0; i<=dt.getRs().length-1; i++)
			{
				Content conDB = new Content();
				conDB.setAnswer(dt.getRs2()[i]);
				conDB.setIdService(contentService.getContentById(Long.parseLong(dt.getRs3()[i])).getIdService());
				conDB.setId(Long.parseLong(dt.getRs3()[i]));
				conDB.setQuestion(dt.getRs()[i]);
				contentService.saveContent(conDB);
			}
			return "redirect:/servicemaster/all";
		}

	@RequestMapping(value="/saveContent", method=RequestMethod.POST, params="saveAndcallGPT")
	public String saveAndcallGPT(@ModelAttribute("content") Content content, Model model) {
		// 1. save content to database
		String ct = content.getQuestion();
		String[] splCT = ct.split(",");
		String[] splAn = content.getAnswer().split(",");
		String prompt = "あなたはプレスリリースを作る業界No.1のプロです。\\n" + //
				"以下の質問と回答をもとに最高のプレスリリースを5つ提案してください。\\n" + //
				"それぞれのプレスリリースはタイトルと文章のセットで作ってください。\\n" + //
				"文章は100文字以内で作ってください。\\n" + //
				"";
		for(int i = 0; i<=splCT.length-1; i++)
		{
			Content conDB = new Content();
			conDB.setAnswer(splAn[i]);
			conDB.setIdService(content.getIdService());
			conDB.setQuestion(splCT[i]);
			prompt = prompt 
					+ "質問" + (i+1) + ".\\n" + //
					" " + splCT[i] + " \n" 
					+ "回答" + (i+1) + ".\\\\n" + //
					" " + splAn[i] + " \n";
			contentService.saveContent(conDB);
		}

		// 2. call API 
      	ChatRequest request = new ChatRequest(modelGPT,
              List.of(new Message("assistant", prompt)),
              maxCompletions,
              temperature,
              maxTokens,
              stop);
		ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
		String re = response.getChoices().get(0).getMessage().getContent();
		System.out.println(re);

		model.addAttribute("resultFromGPT", re);
		return "result";
 	}

}
package net.javaguides.springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import net.javaguides.springboot.dto.gpt.ChatRequest;
import net.javaguides.springboot.dto.gpt.ChatResponse;
import net.javaguides.springboot.dto.gpt.Message;
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

	@Autowired
  private RestTemplate restTemplate;

	@Value("${openai.chatgtp.model}")
	private String model;

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
		String prompt = "あなたはプレスリリースを作る業界No.1のプロです。\n以下の質問と回答をもとに最高のプレスリリースを5つ提案してください。\nそれぞれのプレスリリースはタイトルと文章のセットで作ってください。\n文章は100文字以内で作ってください。\n質問1.\n ターゲットは誰か？\n(年齢層、性別、職業、趣味など、ターゲットとする顧客の詳細)\n回答1. ターゲットはエンジニアです。20代～30代のエンジニアをターゲットとしており、今後のキャリアに不安などを抱いている人をターゲットとしています。\n質問2.\n製品またはサービスの主要な特徴は何か。\n(製品やサービスが提供する主要な利点や機能)\n回答2. このサービスはWEB上でエンジニアの技術スキルや経歴を入力すると適切な転職先の会社を探してくれるサービスです。\n質問3. 競合他社との差別化ポイントは何か？\n回答3. エンジニアのスキル・経歴から最適な会社をAIが自動でマッチングさせることです。トライアルでこのサービスを運用したところ、マッチング精度は90%以上でした。\n質問4. 顧客が抱える問題やニーズは何か？\n回答4. 顧客は自分のスキルや経歴が市場で求められるものなのか、非常に不安になっています。また、転職市場における会社が多すぎて、自分で会社を探すのが困難です。\n質問5. 製品やサービスが顧客に提供する解決策は何か？\n(顧客の問題をどのように解決するか)\n回答5. エンジニアのスキル・経歴から最適な会社をAIが自動でマッチングさせることで、顧客の市場価値を見える化するとともに、転職先の会社を探す負荷を下げます。\n質問6. 顧客に伝えたいブランドイメージは何か？\n回答6. 信頼と安心です。";
      	ChatRequest request = new ChatRequest(model,
              List.of(new Message("assistant", prompt)),
              maxCompletions,
              temperature,
              maxTokens,
              stop);
		ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
		return "redirect:/servicemaster/all";
 	}

}
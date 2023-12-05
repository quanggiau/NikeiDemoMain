package net.javaguides.springboot.controller.gpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ChatController {

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

}

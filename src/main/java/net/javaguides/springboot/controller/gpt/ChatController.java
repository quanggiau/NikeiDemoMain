package net.javaguides.springboot.controller.gpt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.javaguides.springboot.dto.gpt.ChatRequest;
import net.javaguides.springboot.dto.gpt.ChatResponse;
import net.javaguides.springboot.dto.gpt.Message;

@RestController
//@RequestMapping("/api/v1")
public class ChatController {

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

  @PostMapping("/chat")
  public ChatResponse chat(@RequestParam("prompt") String prompt) {

      ChatRequest request = new ChatRequest(model,
              List.of(new Message("user", prompt)),
              maxCompletions,
              temperature,
              maxTokens);

      ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
      return response;
  }
}

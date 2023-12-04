package net.javaguides.springboot.controller.gpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.javaguides.springboot.dto.gpt.ChatRequest;
import net.javaguides.springboot.dto.gpt.ChatResponse;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String apiUrl;

     @Value("${chatgpt.api.key}")
    private String apiKey;

    private static RestTemplate restTemplate = new RestTemplate();

    
    @RequestMapping(value = "/ask", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String ask(@RequestParam String prompt) {
        // create a request
        ChatRequest request = new ChatRequest(model, prompt);
        
        // call the API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
        
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }
        
        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }
}

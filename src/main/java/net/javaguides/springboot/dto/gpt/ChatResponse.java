package net.javaguides.springboot.dto.gpt;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatResponse {
    private List<Choice> choices;

    // constructors, getters and setters
    
    /**
     * @return List<Choice> return the choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

}

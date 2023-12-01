package net.javaguides.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Content {
    @Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private long id;
	
	@Column(name = "id_service")
	private String idService;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "answer")
	private String answer;

    

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the idService
     */
    public String getIdService() {
        return idService;
    }

    /**
     * @param idService the idService to set
     */
    public void setIdService(String idService) {
        this.idService = idService;
    }

    /**
     * @return String return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return String return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

}

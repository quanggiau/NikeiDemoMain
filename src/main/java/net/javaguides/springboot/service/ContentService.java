package net.javaguides.springboot.service;

import java.util.List;
import net.javaguides.springboot.model.Content;

public interface ContentService {
    List<Content> getAllContents();
	void saveContent(Content content);
	Content getContentById(long id);
    Content updateContent(Content content);
}

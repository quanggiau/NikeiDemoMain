package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Content;
import net.javaguides.springboot.repository.ContentRepository;

@Service
public class ContentServiceImpl implements ContentService{
    @Autowired
    private ContentRepository contentRepository;

    public ContentServiceImpl(ContentRepository contentRepository){
        this.contentRepository = contentRepository;
    }

    @Override
    public List<Content> getAllContents() {
       return contentRepository.findAll();

    }

    @Override
    public void saveContent(Content content) {
        contentRepository.save(content);

    }

    @Override
    public Content getContentById(long id) {
        return contentRepository.findById(id);

    }

    @Override
    public Content updateContent(Content content) {
        return contentRepository.save(content);

    }
}

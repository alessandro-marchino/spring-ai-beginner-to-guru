package guru.springframework.springaiimages.services;

import guru.springframework.springaiimages.model.Question;

public interface OllamaService {

    Object getAnswer(Question question);
}

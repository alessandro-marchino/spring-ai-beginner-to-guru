package guru.springframework.airag.services;

import guru.springframework.airag.model.Answer;
import guru.springframework.airag.model.Question;

public interface AIService {

    Answer getAnswer(Question question);
}

package guru.springframework.airagexpert.services;

import guru.springframework.airagexpert.model.Answer;
import guru.springframework.airagexpert.model.Question;

public interface AIService {

    Answer getAnswer(Question question);
}

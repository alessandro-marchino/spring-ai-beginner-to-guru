package guru.springframework.springaiintro.controllers;

import org.springframework.web.bind.annotation.RestController;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.Question;
import guru.springframework.springaiintro.services.OllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final OllamaService ollamaService;

    @PostMapping
    public Answer askQuestion(Question question) {
        return ollamaService.getAnswer(question);
    }

}

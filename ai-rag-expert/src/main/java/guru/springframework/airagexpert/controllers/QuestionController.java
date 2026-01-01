package guru.springframework.airagexpert.controllers;

import org.springframework.web.bind.annotation.RestController;

import guru.springframework.airagexpert.model.Answer;
import guru.springframework.airagexpert.model.Question;
import guru.springframework.airagexpert.services.AIService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final AIService service;;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return service.getAnswer(question);
    }

}

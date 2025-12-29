package guru.springframework.springaiintro.controllers;

import org.springframework.web.bind.annotation.RestController;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.GetCapitalRequest;
import guru.springframework.springaiintro.model.Question;
import guru.springframework.springaiintro.services.OllamaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final OllamaService ollamaService;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        log.info("Invoked askQuestion");
        return ollamaService.getAnswer(question);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody GetCapitalRequest getCapitalRequest) {
        log.info("Invoked getCapitalRequest");
        return ollamaService.getCapital(getCapitalRequest);
    }
}

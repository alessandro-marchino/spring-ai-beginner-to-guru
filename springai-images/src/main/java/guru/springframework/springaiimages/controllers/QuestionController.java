package guru.springframework.springaiimages.controllers;

import org.springframework.web.bind.annotation.RestController;

import guru.springframework.springaiimages.model.Question;
import guru.springframework.springaiimages.services.OllamaService;
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
    public Object askQuestion(@RequestBody Question question) {
        log.info("Invoked askQuestion");
        return ollamaService.getAnswer(question);
    }

}

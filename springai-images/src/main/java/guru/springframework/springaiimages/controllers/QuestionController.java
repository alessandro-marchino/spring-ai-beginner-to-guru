package guru.springframework.springaiimages.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.springaiimages.model.Answer;
import guru.springframework.springaiimages.services.OllamaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final OllamaService ollamaService;

    @PostMapping(value="/lookup-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Answer askImage(@RequestParam MultipartFile file) throws IOException {
        log.info("Invoked askImage");
        return ollamaService.getAnswer(file.getBytes());
    }

}

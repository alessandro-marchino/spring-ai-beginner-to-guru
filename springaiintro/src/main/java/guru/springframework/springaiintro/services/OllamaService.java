package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.Question;
import reactor.core.publisher.Flux;

public interface OllamaService {

    String getAnswer(String question);
    Flux<String> getAnswerStream(String question);

    Answer getAnswer(Question question);
}

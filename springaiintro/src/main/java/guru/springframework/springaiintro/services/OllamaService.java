package guru.springframework.springaiintro.services;

import reactor.core.publisher.Flux;

public interface OllamaService {

    String getAnswer(String question);
    Flux<String> getAnswerStream(String question);
}

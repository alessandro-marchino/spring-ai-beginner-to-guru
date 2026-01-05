package guru.springframework.springaiimages.services;

import org.springframework.lang.NonNull;

import guru.springframework.springaiimages.model.Answer;

public interface OllamaService {

    Answer getAnswer(@NonNull byte[] imageBytes);
}

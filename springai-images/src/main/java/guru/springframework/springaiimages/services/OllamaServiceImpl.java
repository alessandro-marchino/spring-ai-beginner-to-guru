package guru.springframework.springaiimages.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import guru.springframework.springaiimages.model.Answer;
import guru.springframework.springaiimages.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OllamaServiceImpl implements OllamaService {

    @NonNull
    private final ChatModel chatModel;

    @Override
    public Object getAnswer(Question question) {
        return new Answer("");
    }

}

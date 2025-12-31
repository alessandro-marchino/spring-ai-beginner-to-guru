package guru.springframework.airag.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import guru.springframework.airag.model.Answer;
import guru.springframework.airag.model.Question;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OllamaAIService implements AIService {

    private final ChatModel chatModel;

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        return new Answer(chatModel.call(prompt).getResult().getOutput().getText());
    }

}

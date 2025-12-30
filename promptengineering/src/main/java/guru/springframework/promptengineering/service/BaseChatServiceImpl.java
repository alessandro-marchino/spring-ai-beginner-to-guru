package guru.springframework.promptengineering.service;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseChatServiceImpl implements BaseChatService {
    private final ChatModel chatModel;

    @Override
    public String chat(String prompt) {
        return chat(prompt, Map.of(), null);
    }

    @Override
    public String chat(String prompt, @Nullable ChatOptions chatOptions) {
        return chat(prompt, Map.of(), chatOptions);
    }

    @Override
    public String chat(String prompt, Map<String, Object> params) {
        return chat(prompt, params, null);
    }

    @Override
    public String chat(String prompt, Map<String, Object> params, @Nullable ChatOptions chatOptions) {
        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        Prompt promptToSend = promptTemplate.create(params, chatOptions);
        return chat(promptToSend);
    }

    @Override
    public String chat(Prompt prompt) {
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}

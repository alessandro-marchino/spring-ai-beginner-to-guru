package guru.springframework.promptengineering.service;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseChatServiceImpl implements BaseChatService {
    private final ChatModel chatModel;

    @Override
    public String chat(String prompt) {
        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        Prompt promptToSend = promptTemplate.create();

        return chatModel.call(promptToSend).getResult().getOutput().getText();
    }

    @Override
    public String chat(String prompt, Map<String, Object> params) {
        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        Prompt promptToSend = promptTemplate.create(params);
        return chatModel.call(promptToSend).getResult().getOutput().getText();
    }

}

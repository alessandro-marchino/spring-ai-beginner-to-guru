package guru.springframework.promptengineering.service;

import java.util.Map;

import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.lang.Nullable;

public interface BaseChatService {

    String chat(String prompt);
    String chat(String prompt, @Nullable ChatOptions chatOptions);
    String chat(String prompt, Map<String, Object> params);
    String chat(String prompt, Map<String, Object> params, @Nullable ChatOptions chatOptions);
}

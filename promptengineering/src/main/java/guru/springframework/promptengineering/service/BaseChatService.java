package guru.springframework.promptengineering.service;

import java.util.Map;

public interface BaseChatService {

    String chat(String prompt);
    String chat(String prompt, Map<String, Object> params);
}

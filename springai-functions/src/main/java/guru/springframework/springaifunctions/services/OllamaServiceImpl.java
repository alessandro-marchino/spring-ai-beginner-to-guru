package guru.springframework.springaifunctions.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import guru.springframework.springaifunctions.functions.PopulationServiceFunction;
import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.PopulationRequest;
import guru.springframework.springaifunctions.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OllamaServiceImpl implements OllamaService {

    @NonNull
    private final ChatModel chatModel;

    @Value("${sfg.aiapp.api-ninjas-key}")
    private String apiNinjasKey;

    @Override
    public Answer getAnswer(Question question) {
        CallResponseSpec response = ChatClient.builder(chatModel)
            .build()
            .prompt()
            .user(question.question())
            .toolCallbacks(FunctionToolCallback.builder("getPopulation", new PopulationServiceFunction(apiNinjasKey))
                .description("Get the population of a country")
                .inputType(PopulationRequest.class)
                .build())
            .call();

        return new Answer(response.content());
    }

}

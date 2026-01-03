package guru.springframework.springaifunctions.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import guru.springframework.springaifunctions.functions.PopulationServiceFunction;
import guru.springframework.springaifunctions.functions.StockPriceServiceFunction;
import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.PopulationRequest;
import guru.springframework.springaifunctions.model.PopulationResponse;
import guru.springframework.springaifunctions.model.Question;
import guru.springframework.springaifunctions.model.stock.StockPriceRequest;
import guru.springframework.springaifunctions.model.stock.StockPriceResponse;
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
            .system("""
                You are a population service. You reveive population information from a service withi gives you the informations based on historical trends.
                In your response, always give indications on how many historical points you have, and if there are any projections on future data.
                """)
            .toolCallbacks(FunctionToolCallback.builder("getPopulation", new PopulationServiceFunction(apiNinjasKey))
                .description("Get the population of a country")
                .inputType(PopulationRequest.class)
                .toolCallResultConverter((result, type) -> {
                    if(result == null) {
                        return "";
                    }
                    String schema = ModelOptionsUtils.getJsonSchema(PopulationResponse.class, false);
                    String json = ModelOptionsUtils.toJsonString(result);
                    return schema + "\n" + json;
                })
                .build())
            .call();

        return new Answer(response.content());
    }

    @Override
    public Answer getStockData(Question question) {
        CallResponseSpec response = ChatClient.builder(chatModel)
            .build()
            .prompt()
            .user(question.question())
            .system("""
                You are a stock price service. You retreive stock price information from a service which gives you the informations for a given ticker or symbol.
                In your response, always give indications on how the data of the last update not by using the UNIX timestamp, but converting it as a readable date and time based on the Europe/Rome timezone.
                """)
            .toolCallbacks(FunctionToolCallback.builder("getStockPrice", new StockPriceServiceFunction(apiNinjasKey))
                .description("Get the price of a stock")
                .inputType(StockPriceRequest.class)
                .toolCallResultConverter((result, type) -> {
                    if(result == null) {
                        return "";
                    }
                    String schema = ModelOptionsUtils.getJsonSchema(StockPriceResponse.class, false);
                    String json = ModelOptionsUtils.toJsonString(result);
                    return schema + "\n" + json;
                })
                .build())
            .call();

        return new Answer(response.content());
    }

}

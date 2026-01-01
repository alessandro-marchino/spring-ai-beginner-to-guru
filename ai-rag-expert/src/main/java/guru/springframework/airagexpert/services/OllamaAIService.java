package guru.springframework.airagexpert.services;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import guru.springframework.airagexpert.model.Answer;
import guru.springframework.airagexpert.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OllamaAIService implements AIService {

    private final ChatModel chatModel;
    private final VectorStore vectorStore;


    @Value("classpath:/templates/req-prompt-template.st")
    private Resource reqPromptTemplate;
    @Value("classpath:/templates/system-message.st")
    private Resource systemMessageTemplate;

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate systemMessagePromptTemplate = new PromptTemplate(systemMessageTemplate);
        Message systemMessage = systemMessagePromptTemplate.createMessage();

        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
            .query(question.question())
            .topK(4)
            .build());
        List<String> contentList = documents.stream().map(Document::getFormattedContent).toList();

        PromptTemplate promptTemplate = new PromptTemplate(reqPromptTemplate);
        Message userMessage = promptTemplate.createMessage(Map.of(
            "input", question.question(),
            "documents", String.join("\n", contentList)
        ));
        Prompt prompt = new Prompt(systemMessage, userMessage);
        return new Answer(chatModel.call(prompt).getResult().getOutput().getText());
    }

}

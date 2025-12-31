package guru.springframework.airag.services;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import guru.springframework.airag.model.Answer;
import guru.springframework.airag.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OllamaAIService implements AIService {

    private final ChatModel chatModel;
    private final SimpleVectorStore vectorStore;


    @Value("classpath:/templates/req-prompt-template-meta.st")
    private Resource reqPromptTemplate;

    @Override
    public Answer getAnswer(Question question) {
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
            .query(question.question())
            .topK(4)
            .build());
        List<String> contentList = documents.stream().map(Document::getFormattedContent).toList();

        PromptTemplate promptTemplate = new PromptTemplate(reqPromptTemplate);
        Prompt prompt = promptTemplate.create(Map.of(
            "input", question.question(),
            "documents", String.join("\n", contentList)
        ));
        return new Answer(chatModel.call(prompt).getResult().getOutput().getText());
    }

}

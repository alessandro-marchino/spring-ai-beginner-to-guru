package guru.springframework.springaiimages.services;

import org.apache.tika.Tika;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import guru.springframework.springaiimages.model.Answer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OllamaServiceImpl implements OllamaService {

    @NonNull
    private final ChatModel chatModel;

    @Override
    public Answer getAnswer(@NonNull byte[] imageBytes) {
        Tika tika = new Tika();
        String mimeType = tika.detect(imageBytes);

        String response = ChatClient.builder(chatModel)
            .build()
            .prompt()
                .system("Answer the requested question in a plain text formatted manner")
                .user(userMessage -> userMessage
                    .text("Describe the given image")
                    .media(MimeTypeUtils.parseMimeType(mimeType), new ByteArrayResource(imageBytes))
                )
            .call()
            .content();
        log.info("Response: {}", response);

        return new Answer(response);
    }
}

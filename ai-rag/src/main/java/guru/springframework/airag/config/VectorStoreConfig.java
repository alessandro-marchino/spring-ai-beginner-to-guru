package guru.springframework.airag.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
public class VectorStoreConfig {

    @Bean
    SimpleVectorStore simpleVectorStore(@NonNull EmbeddingModel embeddingModel, VectorStoreProperties vectorStoreProperties) {
        return SimpleVectorStore.builder(embeddingModel)

            .build();
    }
}

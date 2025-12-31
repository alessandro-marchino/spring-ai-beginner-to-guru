package guru.springframework.airag.config;

import java.io.File;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class VectorStoreConfig {

    @SuppressWarnings("null")
    @Bean
    SimpleVectorStore simpleVectorStore(@NonNull EmbeddingModel embeddingModel, VectorStoreProperties vectorStoreProperties) {
        SimpleVectorStore store = SimpleVectorStore.builder(embeddingModel).build();
        File vectorStoreFile = new File(vectorStoreProperties.getVectorStorePath());
        if(vectorStoreFile.exists()) {
            store.load(vectorStoreFile);
        } else {
            log.info("Loading documents into vector store");
            vectorStoreProperties.getDocumentsToLoad().forEach(document -> {
                log.info("Loading document: {}", document.getFilename());
                TikaDocumentReader documentReader = new TikaDocumentReader(document);
                List<Document> docs = documentReader.get();
                TextSplitter textSplitter = TokenTextSplitter.builder()
                    .withChunkSize(386)
                    .build();
                List<Document> splitDocs = textSplitter.apply(docs);
                store.add(splitDocs);
            });
            store.save(vectorStoreFile);
        }

        return store;
    }
}

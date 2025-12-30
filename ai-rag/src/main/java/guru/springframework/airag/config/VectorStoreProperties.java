package guru.springframework.airag.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("sfg.aiapp")
@Data
public class VectorStoreProperties {

    private String vectorStorePath;
    private List<String> documentsToLoad;
}

package guru.springframework.springaiintro.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OllamaServiceImplTest {

    @Autowired OllamaServiceImpl service;

    @Test
    void getAnswer() {
        String answer = service.getAnswer("What is the meaning of life?");
        System.out.println(answer);
    }
}

package guru.springframework.springaifunctions.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.Question;

@SpringBootTest
public class OllamaServiceImplTest {

    @Autowired OllamaServiceImpl service;

    @Test
    void getAnswer() {
        Answer answer = service.getAnswer(new Question("What is the meaning of life?"));
        System.out.println(answer.answer());
    }

}

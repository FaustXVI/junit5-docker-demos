package com.github.awesome.quiz;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.awesome.quiz.Answer.answer;
import static com.github.awesome.quiz.Question.question;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswerRepositoryIT {

    private AnswerRepository repository;

    @BeforeEach
    public void createDatabase() {
        MongoClient mongoClient = new MongoClient("my-awesome-cloud");
        MongoDatabase database = mongoClient.getDatabase("awesome-quiz");
        repository = new AnswerRepository(database);
    }

    @Test
    void shouldAddOneDocumentToMongo() {
        Question question = question("Makemake");
        Answer expectedAnswer = answer("Planète du système solaire");

        repository.add(question, expectedAnswer);

        assertThat(repository.getAnswerFor(question)).isEqualTo(expectedAnswer);
    }

}

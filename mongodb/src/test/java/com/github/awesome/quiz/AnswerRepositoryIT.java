package com.github.awesome.quiz;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerRepositoryIT {

    private AnswerRepository repository;

    private MongoDatabase database;

    @BeforeEach
    public void createDatabase() {
        MongoClient mongoClient = new MongoClient("my-awesome-cloud");
        database = mongoClient.getDatabase("awesome-quiz");
        repository = new AnswerRepository(database);
    }

    @Test
    void shouldGetAnswerFromMongo() {
        repository.add("Makemake", "Planète du système solaire");

        String actualAnswer = repository.getAnswerFor("Makemake");

        assertThat(actualAnswer).isEqualTo("Planète du système solaire");
    }

    @AfterEach
    public void dropDatabase() {
        database.drop();
    }

}

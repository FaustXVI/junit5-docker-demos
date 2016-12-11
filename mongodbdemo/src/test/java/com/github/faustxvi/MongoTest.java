package com.github.faustxvi;

import com.github.junit5docker.Docker;
import com.github.junit5docker.Port;
import com.github.junit5docker.WaitFor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Docker(image = "mongo",
        ports = @Port(exposed = 27017, inner = 27017),
        waitFor = @WaitFor("waiting for connections on port"))
public class MongoTest {

    private MongoDatabase database;

    @BeforeEach
    public void createDatabase() {
        MongoClient mongoClient = new MongoClient();
        database = mongoClient.getDatabase("juni5-docker");
    }

    @AfterEach
    public void deleteDatabase() {
        database.drop();
    }

    @Test
    void shouldAddOneDocumentToMongo() {
        MongoCollection<Document> data = database.getCollection("demo");
        data.insertOne(new Document("name", "MongoDB"));
        Assertions.assertEquals(1L, data.count());
    }

    @Test
    void shouldAddTwoDocumentsToMongo() {
        MongoCollection<Document> data = database.getCollection("demo");
        data.insertOne(new Document("name", "MongoDB"));
        data.insertOne(new Document("name", "Meetup"));
        Assertions.assertEquals(2L, data.count());
    }
}

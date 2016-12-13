package com.github.faustxvi;

import com.github.junit5docker.Docker;
import com.github.junit5docker.Port;
import com.github.junit5docker.WaitFor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Docker(image = "mongo",
        ports = @Port(exposed = 27017, inner = 27017),
        waitFor = @WaitFor("waiting for connections on port"))
public class MongoTest {

    private MongoDatabase database;

    private UseMongo useMongo;

    @BeforeEach
    public void createDatabase() {
        MongoClient mongoClient = new MongoClient();
        database = mongoClient.getDatabase("junit5-docker");
        useMongo = new UseMongo(database);
    }

    @AfterEach
    public void deleteDatabase() {
        database.drop();
    }

    @Test
    void shouldAddOneDocumentToMongo() {
        useMongo.addUser("MongoDB");
        Assertions.assertEquals(1L, useMongo.count());
    }

    @Test
    void shouldAddTwoDocumentsToMongo() {
        useMongo.addUser("MongoDB");
        useMongo.addUser("Meetup");
        Assertions.assertEquals(2L, useMongo.count());
    }
}

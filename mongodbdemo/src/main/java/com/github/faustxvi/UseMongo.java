package com.github.faustxvi;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class UseMongo {


    private final MongoCollection<Document> collection;


    public UseMongo(MongoDatabase database) {
        this.collection = database.getCollection("users");
    }

    public void addUser(String username) {
        collection.insertOne(new Document("username", username));
    }

    public long count() {
        return collection.count();
    }
}

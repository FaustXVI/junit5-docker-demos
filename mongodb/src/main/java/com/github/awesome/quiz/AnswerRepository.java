package com.github.awesome.quiz;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class AnswerRepository {

    private static final String QUESTION_KEY = "question";

    private static final String ANSWER_KEY = "answer";

    private final MongoCollection<Document> databaseCollection;

    public AnswerRepository(MongoDatabase database) {
        databaseCollection = database.getCollection("answers");
    }

    public void add(String question, String answer) {
        Map<String, Object> doc = new HashMap<>();
        doc.put(QUESTION_KEY, question);
        doc.put(ANSWER_KEY, answer);
        databaseCollection.insertOne(new Document(doc));
    }

    public String getAnswerFor(String question) {
        FindIterable<Document> documents = databaseCollection.find(new Document(QUESTION_KEY, question));
        return (String) documents.first().get(ANSWER_KEY);
    }
}

package com.github.awesome.quiz;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class AnswerRepository {

    public static final String QUESTION_KEY = "question";

    public static final String ANSWER_KEY = "answer";

    private final MongoCollection<Document> databaseCollection;

    public AnswerRepository(MongoDatabase database) {
        databaseCollection = database.getCollection("answers");
    }

    public void add(Question question, Answer answer) {
        Map<String, Object> doc = new HashMap<>();
        doc.put(QUESTION_KEY, question.value);
        doc.put(ANSWER_KEY, answer.value);
        databaseCollection.insertOne(new Document(doc));
    }

    public Answer getAnswerFor(Question question) {
        FindIterable<Document> documents = databaseCollection.find(new Document(QUESTION_KEY, question.value));
        return Answer.answer((String) documents.first().get(ANSWER_KEY));
    }
}

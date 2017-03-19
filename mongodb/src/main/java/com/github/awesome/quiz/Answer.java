package com.github.awesome.quiz;

import java.util.Objects;

public final class Answer {

    final String value;

    public Answer(String value) {
        this.value = value;
    }

    public static Answer answer(String answer) {
        return new Answer(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(value, answer.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

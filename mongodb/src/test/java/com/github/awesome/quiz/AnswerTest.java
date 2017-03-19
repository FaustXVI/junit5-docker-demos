package com.github.awesome.quiz;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class AnswerTest {

    @Test
    void shouldValidateEqualsContract() {
        EqualsVerifier.forClass(Answer.class).verify();
    }
}
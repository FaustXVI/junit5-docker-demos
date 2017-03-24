package com.github.awesome.quiz;

import java.time.Duration;
import java.util.Optional;

import static java.util.Optional.empty;

public class CorrectedAnswer {

    public final Optional<Duration> time;

    public boolean correct;

    private CorrectedAnswer(boolean correct) {
        this(correct, empty());
    }

    private CorrectedAnswer(boolean correct, Optional<Duration> time) {
        this.correct = correct;
        this.time = time;
    }

    public static CorrectedAnswer wrongAnswer() {
        return new CorrectedAnswer(false);
    }

    public static CorrectedAnswer correctAnswer(Duration time) {
        return new CorrectedAnswer(true, Optional.of(time));
    }

}

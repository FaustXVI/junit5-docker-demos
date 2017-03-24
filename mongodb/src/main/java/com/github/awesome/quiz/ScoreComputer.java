package com.github.awesome.quiz;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

public class ScoreComputer {

    private final int winnerPoints;

    public ScoreComputer(int winnerPoints) {
        this.winnerPoints = winnerPoints;
    }

    public List<Integer> pointsGrantedInstance(CorrectedAnswer... answers) {
        Optional<Duration> fastest = stream(answers)
            .filter(a -> a.correct)
            .map(a -> a.time.get())
            .min(naturalOrder());
        return stream(answers).map(a -> a.correct && a.time.equals(fastest) ? winnerPoints : 0).collect(toList());
    }
}

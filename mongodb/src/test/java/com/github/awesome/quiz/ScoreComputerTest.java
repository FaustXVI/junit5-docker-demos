package com.github.awesome.quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.awesome.quiz.CorrectedAnswer.correctAnswer;
import static com.github.awesome.quiz.CorrectedAnswer.wrongAnswer;
import static java.time.Duration.ofMillis;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Score computation")
class ScoreComputerTest {

    private ScoreComputer scoreComputer;

    @Nested
    @DisplayName("for a one point question")
    class ForAOnePointQuestion {

        @BeforeEach
        void createScoreComputer() {
            scoreComputer = new ScoreComputer(1);
        }

        @Nested
        @DisplayName("on a two player game")
        class OnATwoPlayerGame {

            @Nested
            @DisplayName("with both players wrong")
            class WithBothPlayersWrong {

                @Test
                @DisplayName("should not give points")
                void shouldNotGivePoints() {
                    assertThat(scoreComputer.pointsGrantedInstance(wrongAnswer(), wrongAnswer())).containsExactly(0, 0);
                }

            }

            @Nested
            @DisplayName("with only one right answer")
            class WithOnlyOneRightAnswer {

                @Test
                @DisplayName("should give points to the first player alone")
                void shouldGivePointsToTheWinnerOnly() {
                    assertThat(scoreComputer.pointsGrantedInstance(correctAnswer(ofMillis(10)), wrongAnswer())).containsExactly(1, 0);
                }

                @Test
                @DisplayName("should give points to the second player alone")
                void shouldGivePointsToTheWinnerEvenIfSecondPlayer() {
                    assertThat(scoreComputer.pointsGrantedInstance(wrongAnswer(), correctAnswer(ofMillis(10)))).containsExactly(0, 1);
                }

            }

            @Nested
            @DisplayName("with both answers correct")
            class WithBothAnswersCorrect {

                @Test
                @DisplayName("should give points to the fastest player if both are correct (first player case)")
                void shouldGivePointsToTheFastestPlayerIfBothAreCorrectFirstPlayer() {
                    assertThat(scoreComputer.pointsGrantedInstance(correctAnswer(ofMillis(10)), correctAnswer(ofMillis(100))))
                        .containsExactly(1, 0);
                }

                @Test
                @DisplayName("should give points to the fastest player if both are correct (second player case)")
                void shouldGivePointsToTheFastestPlayerIfBothAreCorrectSecondPlayer() {
                    assertThat(scoreComputer.pointsGrantedInstance(correctAnswer(ofMillis(100)), correctAnswer(ofMillis(10))))
                        .containsExactly(0, 1);
                }

            }

        }

        @Nested
        @DisplayName("on a three player game")
        class WithThreePlayers {

            @Nested
            @DisplayName("with no correct answer")
            class WithBothPlayersWrong {

                @Test
                @DisplayName("should not give points")
                void shouldNotGivePoints() {
                    assertThat(scoreComputer.pointsGrantedInstance(wrongAnswer(), wrongAnswer(), wrongAnswer())).containsExactly(0, 0, 0);
                }

            }

            @Nested
            @DisplayName("with only one right answer")
            class WithOnlyOneRightAnswer {

                @Test
                @DisplayName("should give points to the first player alone")
                void shouldGivePointsToTheWinnerOnly() {
                    assertThat(scoreComputer.pointsGrantedInstance(correctAnswer(ofMillis(10)), wrongAnswer(), wrongAnswer()))
                        .containsExactly(1, 0, 0);
                }

            }

            @Nested
            @DisplayName("with more than one right answer")
            class WithMoreThanOneRightAnswer {

                @Test
                @DisplayName("should give points to the fastest")
                void shouldGivePointsToTheWinnerOnly() {
                    assertThat(scoreComputer.pointsGrantedInstance(correctAnswer(ofMillis(100)), correctAnswer(ofMillis(10)), correctAnswer(ofMillis(1)))
                    )
                        .containsExactly(0, 0, 1);
                }

            }

        }

    }

    @Nested
    @DisplayName("for a two point question")
    class ForATwoPointQuestion {

        @BeforeEach
        void createScoreComputer() {
            scoreComputer = new ScoreComputer(2);
        }

        @Nested
        @DisplayName("with one correct answer")
        class WithOneCorrectAnswer {

            @Test
            @DisplayName("should give points to the winner")
            void shouldGivePointsToTheWinnerOnly() {
                assertThat(scoreComputer.pointsGrantedInstance(correctAnswer(ofMillis(100)), wrongAnswer()))
                    .containsExactly(2, 0);
            }

        }

    }

}

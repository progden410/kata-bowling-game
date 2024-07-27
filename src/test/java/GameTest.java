import io.kata.bowlinggame.BowlingGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class GameTest {
    @ParameterizedTest
    @MethodSource
    public void Given_a_bowling_game_When_roll_pins_Then_frame_increase(String rolls, int expectScore, int expectFrames){
        var game = new BowlingGame();

        Arrays.stream(rolls.split(","))
                .filter(pins -> !pins.equals(""))
                .mapToInt(pins -> Integer.parseInt(pins.trim()))
                .forEach(pins -> game.roll(pins));

        Assertions.assertEquals(expectScore, game.score());
        Assertions.assertEquals(expectFrames, game.frames());
    }

    public static Stream<Arguments> Given_a_bowling_game_When_roll_pins_Then_frame_increase(){
        return Stream.of(
                Arguments.of("1", 1, 1),
                Arguments.of("1,1", 2, 2),
                Arguments.of("1,1,1,1", 4, 3),
                Arguments.of("1,1,1,1,1,1", 6, 4),
                Arguments.of("1,1,1,1,1,1,1,1", 8, 5),
                Arguments.of("1,1,1,1,1,1,1,1,1,1", 10, 6),
                Arguments.of("1,1,1,1,1,1,1,1,1,1,1,1", 12, 7),
                Arguments.of("1,1,1,1,1,1,1,1,1,1,1,1,1,1", 14, 8),
                Arguments.of("1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1", 16, 9),
                Arguments.of("1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1", 18, 10),
                Arguments.of("1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1", 20, 10),
                Arguments.of("0,10", 10, 2),
                Arguments.of("0", 0, 1),
                Arguments.of("", 0, 1)
        );
    }
}

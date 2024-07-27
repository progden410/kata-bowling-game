import io.kata.bowlinggame.BonusType;
import io.kata.bowlinggame.BowlingGame;
import io.kata.bowlinggame.Frame;
import io.kata.bowlinggame.MockBowlingGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private static final Logger logger = LoggerFactory.getLogger(GameTest.class);

    private Frame f;

    @ParameterizedTest
    @MethodSource
    public void Given_a_bowling_game_When_roll_pins_Then_frame_increase(String rolls, int expectScore, int expectFrames) {
        var game = new MockBowlingGame();

        Arrays.stream(rolls.split(","))
                .filter(pins -> !pins.equals(""))
                .mapToInt(pins -> Integer.parseInt(pins.trim()))
                .forEach(pins -> game.roll(pins));

        assertEquals(expectScore, game.score());
        assertEquals(expectFrames, game.frames());
    }

    @Test
    public void spare_strike() {
        newFrame();
        assertEquals(BonusType.Normal, f.bonusType());
        newFrame(0, 10);
        assertEquals(BonusType.Spare, f.bonusType());
        f.addBonus(5);
        f.addBonus(5);
        logger.info(() -> f.toString());
        assertEquals(15, f.score());
        newFrame(5, 5);
        assertEquals(BonusType.Spare, f.bonusType());
        newFrame(1, 9);
        assertEquals(BonusType.Spare, f.bonusType());
        newFrame(10);
        assertEquals(BonusType.Strike, f.bonusType());
        f.addBonus(10);
        f.addBonus(10);
        f.addBonus(10);
        logger.info(() -> f.toString());
        assertEquals(30, f.score());
    }

    private void newFrame(int... pins) {
        f = Frame.of(1);
        for (int pin : pins) {
            f.roll(pin);
        }
    }

    public static Stream<Arguments> Given_a_bowling_game_When_roll_pins_Then_frame_increase() {
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
                Arguments.of("0,10,5", 20, 2),
                Arguments.of("5,5,0,10, 9,0", 38, 4),
                Arguments.of("10,10,10", 60, 4),
                // 1-9 frames
                Arguments.of("10,10,10,10,10,10,10,10,10", 240, 10),
                // 10 frames
                Arguments.of("10,10,10,10,10,10,10,10,10,10,10,10", 300, 10),
                Arguments.of("10,10,10,10,10,10,10,10,10,10,10,9", 299, 10),
                Arguments.of("10,10,10,10,10,10,10,10,10,10,10,0", 290, 10),
                Arguments.of("10,10,10,10,10,10,10,10,10,10,9,0", 288, 10),
                Arguments.of("10,10,10,10,10,10,10,10,10,10,0,0", 270, 10),
                Arguments.of("10,10,10,10,10,10,10,10,10,9,0,0", 267, 10),
                Arguments.of("10,10,10,10,10,10,10,10,10,1,0,0", 243, 10),

                Arguments.of("10,10,10,10,10,10,10,10,10,10,10,10,1", 300, 10),

                Arguments.of("0", 0, 1),
                Arguments.of("", 0, 1)
        );
    }
}

package io.kata.bowlinggame;

import java.util.LinkedList;
import java.util.List;

public class BowlingGame {
    private final List<Frame> frames;

    public BowlingGame() {
        this.frames = new LinkedList<>();
        frames.add(Frame.of(frames.size() + 1));
    }

    public void roll(int pins) {
        frames.getLast().roll(pins);

        var prevFrames = frames.stream().filter(f -> f != frames.getLast()).toList();
        prevFrames.stream()
                .filter(f -> f.bonusType() == BonusType.Spare || f.bonusType() == BonusType.Strike)
                .forEach(f -> f.addBonus(pins));

        if (frames.getLast().isDone() && frames.size() < 10) {
            frames.add(Frame.of(frames.size() + 1));
        }
    }

    public int score() {
        return frames.stream().mapToInt(f -> f.score()).sum();
    }

    public int frames() {
        return frames.size();
    }
}

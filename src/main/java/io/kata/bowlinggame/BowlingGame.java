package io.kata.bowlinggame;

import java.util.LinkedList;
import java.util.List;

public class BowlingGame {
    private final List<Frame> frames;

    public BowlingGame() {
        this.frames = new LinkedList<>();
        frames.add(new Frame(frames.size() + 1));
    }

    public void roll(int pins) {
        frames.getLast().roll(pins);

        // spare
        if (frames.size() > 1 && frames.get(frames.size() - 2).bounsType() == BonusType.Spare) {
            if (frames.get(frames.size() - 2).bonusSize() == 0) {
                frames.get(frames.size() - 2).addBouns(pins);
            }
        }

        if (frames.getLast().isDone() && frames.size() < 10) {
            frames.add(new Frame(frames.size() + 1));
        }
    }

    public int score() {
        return frames.stream().mapToInt(f -> f.score()).sum();
    }

    public int frames() {
        return frames.size();
    }
}

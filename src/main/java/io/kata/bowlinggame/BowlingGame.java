package io.kata.bowlinggame;

import java.util.LinkedList;
import java.util.List;

public class BowlingGame {
    protected final List<Frame> frames;

    public BowlingGame() {
        this.frames = new LinkedList<>();
        frames.add(Frame.of(frames.size() + 1));
    }

    public void roll(int pins) {
        // 分數加在最後一格
        frames.getLast().roll(pins);

        // 扣除最後一格，把加分分數加在 spare 或 strike 的格子上
        var prevFrames = frames.stream().filter(f -> f != frames.getLast()).toList();
        prevFrames.stream()
                .filter(f -> f.bonusType() == BonusType.Spare || f.bonusType() == BonusType.Strike)
                .forEach(f -> f.addBonus(pins));

        // 如果當前這格已經完成，加一格
        if (frames.getLast().isDone() && frames.size() < 10) {
            frames.add(Frame.of(frames.size() + 1));
        }
    }

    public int score() {
        return frames.stream().mapToInt(f -> f.score()).sum();
    }

}

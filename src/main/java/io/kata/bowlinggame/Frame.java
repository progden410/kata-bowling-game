package io.kata.bowlinggame;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Frame {
    private final int frameNum;
    List<Integer> pins = new LinkedList<>();

    public Frame(int frameNum) {
        this.frameNum = frameNum;
    }

    public boolean isDone() {
        return pins.size() == 2
                || (pins.size() > 0 && pins.getFirst() == 10);
    }

    public void roll(int pin) {
        pins.add(pin);
    }

    public Integer score() {
        return pins.stream().mapToInt(p->p).sum();
    }
}

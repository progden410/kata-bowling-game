package io.kata.bowlinggame;

public class ErrorFrame extends Frame {
    public ErrorFrame(int frameNum) {
        super(frameNum);
    }

    @Override
    public void roll(int pin) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void addBonus(int pins) {
        throw new java.lang.UnsupportedOperationException();
    }
}

package io.kata.bowlinggame;

public class TenthFrame extends Frame {
    public TenthFrame(int frameNum) {
        super(frameNum);
    }

    @Override
    public void roll(int pin) {
        if (this.bonusType() != BonusType.Normal && pins.size() < 3) {
            pins.add(pin);
        } else if (this.bonusType() == BonusType.Normal && pins.size() < 2) {
            pins.add(pin);
        }

    }
}

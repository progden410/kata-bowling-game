package io.kata.bowlinggame;

import java.util.LinkedList;
import java.util.List;

public class Frame {
    public static Frame of(int frameNum) {
        if (1 <= frameNum && frameNum <= 10) {
            return new Frame(frameNum);
        } else if (frameNum == 10) {
            return new TenthFrame(10);
        }
        return new ErrorFrame(frameNum);
    }

    private final int frameNum;
    List<Integer> pins = new LinkedList<>();
    List<Integer> bonus = new LinkedList<>();

    private BonusType bonusType = BonusType.Normal;

    protected Frame(int frameNum) {
        this.frameNum = frameNum;
    }

    public boolean isDone() {
        return pins.size() == 2
                || (pins.size() > 0 && pins.getFirst() == 10);
    }

    public void roll(int pin) {
        pins.add(pin);
        setBounsType();
    }

    private void setBounsType() {
        bonusType = isStrike() ? BonusType.Strike :
                isSpare() ? BonusType.Spare :
                        BonusType.Normal;
    }

    private boolean isSpare() {
        return pins.size() == 2 && pins.get(0) + pins.get(1) == 10;
    }

    private boolean isStrike() {
        return pins.size() == 1 && pins.get(0) == 10;
    }

    public Integer score() {
        return pins.stream().mapToInt(p -> p).sum() +
                bonus.stream().mapToInt(p -> p).sum()
                ;
    }

    public BonusType bonusType() {
        return bonusType;
    }

    public int bonusSize() {
        return bonus.size();
    }

    public void addBonus(int pins) {
        if (bonusType == BonusType.Spare && bonusSize() < 1) {
            bonus.add(pins);
        }
        if (bonusType == BonusType.Strike && bonusSize() < 2) {
            bonus.add(pins);
        }

    }

    @Override
    public String toString() {
        return "Frame" +
                "[" + frameNum + "][" + bonusType + "] = " + score() +
                ", pins=" + pins +
                ", bonus=" + bonus;
    }
}

package io.kata.bowlinggame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;

public class TenthFrame extends Frame {
    private static final Logger logger = LogManager.getLogger(TenthFrame.class);

    public TenthFrame(int frameNum) {
        super(frameNum);
    }

    @Override
    public void roll(int pin) {
        logger.debug(this);
        if (this.bonusType() == BonusType.Normal && pins.size() < 2) {
            super.roll(pin);
        } else if (EnumSet.of(BonusType.Spare, BonusType.Strike).contains(this.bonusType) && pins.size() < 3) {
            // 只要有 spare 或 strike 就可以打到三球
            // 這時候不用在 setBonusType，不須要再改狀態了
            // 如果改了會回到 normal 會吃不到第三球
            pins.add(pin);
        }
    }

    @Override
    public String toString() {
        return "TenthFrame" +
                "[" + frameNum + "][" + bonusType + "] = " + score() +
                ", pins=" + pins +
                ", bonus=" + bonus;
    }
}

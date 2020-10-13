package ru.sbt.mipt.oop.state;

import ru.sbt.mipt.oop.Signalization;

public class AlarmModeState implements State {
    private final transient Signalization signalization;


    public AlarmModeState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        signalization.deactivate(code);
    }

    @Override
    public void switchToAlarmMode() {
    }
}

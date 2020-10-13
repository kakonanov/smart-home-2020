package ru.sbt.mipt.oop.state;

import ru.sbt.mipt.oop.Signalization;

public class DeactivatedState implements State {
    private final transient Signalization signalization;


    public DeactivatedState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
        signalization.activate(code);
        signalization.setState(new ActivatedState(signalization));
    }

    @Override
    public void deactivate(String code) {}

    @Override
    public void switchToAlarmMode() {
        signalization.setState(new AlarmModeState(signalization));
    }
}

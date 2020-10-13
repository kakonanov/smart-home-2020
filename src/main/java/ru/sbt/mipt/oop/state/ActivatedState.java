package ru.sbt.mipt.oop.state;

import ru.sbt.mipt.oop.Signalization;

public class ActivatedState implements State {
    private final transient Signalization signalization;


    public ActivatedState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {}

    @Override
    public void deactivate(String code) {
        signalization.deactivate(code);
        if (signalization.getState().getClass().equals(ActivatedState.class)) {
            signalization.setState(new DeactivatedState(signalization));
        }
    }

    @Override
    public void switchToAlarmMode() {
        signalization.setState(new AlarmModeState(signalization));
    }
}

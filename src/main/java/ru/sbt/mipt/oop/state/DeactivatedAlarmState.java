package ru.sbt.mipt.oop.state;

import ru.sbt.mipt.oop.Signalization;

public class DeactivatedAlarmState implements AlarmState {
    private final transient Signalization signalization;


    public DeactivatedAlarmState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
        signalization.setCode(code);
        signalization.setState(new ActivatedAlarmState(signalization));
        System.out.println("Signalization was activated");
    }

    @Override
    public void deactivate(String code) {
        System.out.println("Signalization has already been deactivated");

    }

    @Override
    public void switchToAlarmMode() {
        signalization.setState(new AlarmModeAlarmState(signalization));
    }
}

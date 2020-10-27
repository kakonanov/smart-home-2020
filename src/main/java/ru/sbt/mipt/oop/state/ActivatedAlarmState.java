package ru.sbt.mipt.oop.state;

import ru.sbt.mipt.oop.Signalization;

public class ActivatedAlarmState implements AlarmState {
    private final transient Signalization signalization;


    public ActivatedAlarmState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
        System.out.println("Signalization has already been deactivated");
    }

    @Override
    public void deactivate(String code) {
        if (signalization.isCodeCorrect(code)) {
            signalization.setCode(null);
            signalization.setState(new DeactivatedAlarmState(signalization));
            System.out.println("Signalization was deactivated");

        } else {
            switchToAlarmMode();
        }
    }

    @Override
    public void switchToAlarmMode() {
        signalization.setState(new AlarmModeAlarmState(signalization));
        System.out.println("Alarm!!");
    }
}

package ru.sbt.mipt.oop.state;

import ru.sbt.mipt.oop.Signalization;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.logging.SocketHandler;

public class AlarmModeAlarmState implements AlarmState {
    private final transient Signalization signalization;


    public AlarmModeAlarmState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
        System.out.println("Signalization is in the alarm mode");
    }

    @Override
    public void deactivate(String code) {
        if (signalization.isCodeCorrect(code)) {
            signalization.setCode(null);
            signalization.setState(new DeactivatedAlarmState(signalization));
            System.out.println("Signalization was deactivated");

        } else {
            System.out.println("Signalization is in the alarm mode");
        }
    }

    @Override
    public void switchToAlarmMode() {
        System.out.println("Signalization is in the alarm mode");
    }
}

package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.state.ActivatedAlarmState;
import ru.sbt.mipt.oop.state.AlarmModeAlarmState;
import ru.sbt.mipt.oop.state.AlarmState;
import ru.sbt.mipt.oop.state.DeactivatedAlarmState;

import java.util.Objects;

public class Signalization {
    private AlarmState alarmState;
    private String code;

    Signalization() {
        this.alarmState = new DeactivatedAlarmState(this);
    }

    public boolean isActivatedAlarmState() {
        return Objects.equals(alarmState.getClass(), ActivatedAlarmState.class);
    }

    public boolean isDeactivatedAlarmState() {
        return Objects.equals(alarmState.getClass(), DeactivatedAlarmState.class);
    }

    public boolean isAlarmModeAlarmState() {
        return Objects.equals(alarmState.getClass(), AlarmModeAlarmState.class);
    }

    public boolean isCodeCorrect(String code) {
        return this.code.equals(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void activate(String code) {
        alarmState.activate(code);
    }

    public void deactivate(String code) {
        alarmState.deactivate(code);
    }

    public void setState(AlarmState alarmState){
        this.alarmState = alarmState;
    }
}

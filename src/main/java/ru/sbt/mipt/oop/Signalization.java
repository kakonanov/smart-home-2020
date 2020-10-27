package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.domain.Actionable;
import ru.sbt.mipt.oop.state.DeactivatedAlarmState;
import ru.sbt.mipt.oop.state.AlarmState;

public class Signalization {
    private AlarmState alarmState;
    private String code;

    Signalization() {
        this.alarmState = new DeactivatedAlarmState(this);
    }

    public AlarmState getState() {
        return alarmState;
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

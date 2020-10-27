package ru.sbt.mipt.oop.state;

public interface AlarmState {
    void activate(String code);
    void deactivate(String code);
    void switchToAlarmMode();
}

package ru.sbt.mipt.oop.state;

public interface State {
    void activate(String code);
    void deactivate(String code);
    void switchToAlarmMode();
}

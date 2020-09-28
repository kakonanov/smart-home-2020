package ru.sbt.mipt.oop;

public class LightHandler {
    private Light light;

    LightHandler(Light light) {
        this.light = light;
    }

    public void turnOn() {
        light.setOn(true);
    }

    public void turnOff() {
        light.setOn(false);
    }
}

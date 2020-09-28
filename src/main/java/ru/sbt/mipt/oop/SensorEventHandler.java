package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class SensorEventHandler {
    private final SmartHome smartHome;

    SensorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handle(SensorEvent sensorEvent) {
        System.out.println("Got event: " + sensorEvent);
        if (sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF) {
            new LightEventHandler(smartHome).handle(sensorEvent);
        }
        else if (sensorEvent.getType() == DOOR_OPEN || sensorEvent.getType() == DOOR_CLOSED) {
            new DoorEventHandler(smartHome).handle(sensorEvent);
        }
    }
}

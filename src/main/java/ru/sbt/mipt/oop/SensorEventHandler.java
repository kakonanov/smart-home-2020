package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class SensorEventHandler {
    private SmartHome smartHome;

    SensorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handle(SensorEvent sensorEvent) {
        System.out.println("Got event: " + sensorEvent);
        if (sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF) {
            handleLightEvent(sensorEvent);
        }
        else if (sensorEvent.getType() == DOOR_OPEN || sensorEvent.getType() == DOOR_CLOSED) {
            handleDoorEvent(sensorEvent);
        }
    }

    private void handleLightEvent(SensorEvent sensorEvent) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(sensorEvent.getObjectId())) {
                    LightHandler lightHandler = new LightHandler(light);
                    if (sensorEvent.getType() == LIGHT_ON) {
                        lightHandler.turnOn();
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        lightHandler.turnOff();
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }

    private void handleDoorEvent(SensorEvent sensorEvent) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(sensorEvent.getObjectId())) {
                    DoorHandler doorHandler = new DoorHandler(door);
                    if (sensorEvent.getType() == DOOR_OPEN) {
                        doorHandler.open();
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        doorHandler.close();
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            new AllLightHandler(smartHome).turnOff();
                        }
                    }
                }
            }
        }
    }
}

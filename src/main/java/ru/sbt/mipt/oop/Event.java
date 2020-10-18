package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.type.EventType;

public class Event {
    private final EventType type;
    private final String objectId;

    public Event(EventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public EventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}

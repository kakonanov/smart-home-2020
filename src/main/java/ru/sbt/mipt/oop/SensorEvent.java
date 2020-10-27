package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.type.EventType;

import static ru.sbt.mipt.oop.type.EventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.type.EventType.ALARM_DEACTIVATE;

public class SensorEvent {
    private final EventType type;
    private final String objectId;
    private String code;

    public SensorEvent(EventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }


    public String getCode() {
        if (type == ALARM_ACTIVATE || type == ALARM_DEACTIVATE)
            return code;
        return "";
    }

    public void setCode(String code) {
        if (type == ALARM_ACTIVATE || type == ALARM_DEACTIVATE)
            this.code = code;
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

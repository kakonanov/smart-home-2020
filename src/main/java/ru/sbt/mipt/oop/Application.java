package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = new SmartHomeReader().readFromJs("smart-home-1.js");
        // начинаем цикл обработки событий

        SensorEventHandler sensorEventHandler = new SensorEventHandler(smartHome);
        SensorEvent sensorEvent = getNextSensorEvent();

        while (sensorEvent != null) {
            sensorEventHandler.handle(sensorEvent);
            sensorEvent = getNextSensorEvent();
        }
    }


    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}

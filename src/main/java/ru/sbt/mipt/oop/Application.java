package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {
    private final SensorEventCicle sensorEventCicle;

    Application() {
        SmartHome smartHome = new SmartHomeReader().readFromJs("smart-home-1.js");
        sensorEventCicle = new SensorEventCicle(smartHome);
    }

    public static void main(String... args) {
        Application application = new Application();
        application.sensorEventCicle.run();
    }
}

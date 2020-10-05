package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventcircle.EventCircle;
import ru.sbt.mipt.oop.eventcircle.EventCircleImpl;
import ru.sbt.mipt.oop.eventgenerator.EventGenerator;
import ru.sbt.mipt.oop.eventgenerator.EventGeneratorImpl;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReaderFromJs;

public class Application {
    private final EventCircle eventCircle;
    private final SmartHome smartHome;

    Application(SmartHomeReader smartHomeReader, EventGenerator eventGenerator) {
        smartHome = smartHomeReader.read();
        eventCircle = new EventCircleImpl(smartHome, eventGenerator);
    }

    public void run() {
        eventCircle.run();
    }

    public static void main(String... args) {
        Application application = new Application(new SmartHomeReaderFromJs("smart-home-1.js"), new EventGeneratorImpl());
        application.run();
    }
}

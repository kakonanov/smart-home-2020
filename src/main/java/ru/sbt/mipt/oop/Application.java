package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventcircle.EventCircle;
import ru.sbt.mipt.oop.eventcircle.EventCircleImpl;
import ru.sbt.mipt.oop.eventgenerator.EventGenerator;
import ru.sbt.mipt.oop.eventgenerator.EventGeneratorImpl;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReaderFromJs;

public class Application {
    private final EventCircle eventCircle;

    Application(EventCircle eventCircle) {
        this.eventCircle = eventCircle;
    }

    public void run() {
        eventCircle.run();
    }

    public static void main(String... args) {
        SmartHomeReader smartHomeReader = new SmartHomeReaderFromJs("smart-home-1.js");
        SmartHome smartHome = smartHomeReader.read();
        EventCircle eventCircle = new EventCircleImpl(smartHome, new EventGeneratorImpl());
        Application application = new Application(eventCircle);
        application.run();
    }
}

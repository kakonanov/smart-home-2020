package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventcircle.EventCircle;
import ru.sbt.mipt.oop.eventcircle.EventCircleImpl;
import ru.sbt.mipt.oop.eventgenerator.EventGenerator;
import ru.sbt.mipt.oop.eventgenerator.EventGeneratorImpl;
import ru.sbt.mipt.oop.eventhandler.*;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReaderFromJs;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        SenderAlarmMessage senderAlarmMessage = new SenderAlarmMessage();
        EventCircle eventCircle = new EventCircleImpl(
                new EventGeneratorImpl(),
                Stream.of(
                        new StartEventHandler(),
                        new DoorEventHandler(smartHome),
                        new LightEventHandler(smartHome),
                        new AllLightEventHandler(smartHome),
                        new SignalizationEventHandler(smartHome))
                    .map(eventHandler -> new AlarmDecoratorEventHandler(eventHandler, smartHome, senderAlarmMessage))
                    .collect(Collectors.toList()));
        Application application = new Application(eventCircle);
        application.run();
    }
}

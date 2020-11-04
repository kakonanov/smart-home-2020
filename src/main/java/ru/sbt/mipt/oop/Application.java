package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.command.Command;

public class Application {
    public static void main(String... args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        Command command = (Command) context.getBean("closeDoorHallCommand");
        command.execute();
//        sensorEventsManager.start();
    }
}

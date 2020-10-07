package ru.sbt.mipt.oop.eventhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.type.EventType;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LightEventHandlerTest {
    private EventHandler eventHandler;
    private SensorEvent sensorEvent;
    private SmartHome smartHome;
    private Door door1;
    private Door door2;
    private Light light1;
    private Light light2;

    @BeforeEach
    void beforeEach(){
        light1 = new Light("1", false);
        light2 = new Light("2", true);
        door1 = new Door(false, "1");
        door2 = new Door(true, "2");
        smartHome = new SmartHome(Arrays.asList(new Room(
                Arrays.asList(light1, light2),
                Arrays.asList(door1, door2),
                "test room")));
        eventHandler = new LightEventHandler(smartHome);
    }

    @Test
    void handleEventWithTypeLightOn() {
        // given
        sensorEvent = new SensorEvent(EventType.LIGHT_ON, "1");
        //when
        eventHandler.handle(sensorEvent);
        //then
        assertTrue(light1.isOn());
    }

    @Test
    void handleEventWithTypeLightOff() {
        //given
        sensorEvent = new SensorEvent(EventType.LIGHT_OFF, "2");
        //when
        eventHandler.handle(sensorEvent);
        //then
        assertFalse(light2.isOn());
    }
}
package ru.sbt.mipt.oop.eventhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.Signalization;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.state.ActivatedAlarmState;
import ru.sbt.mipt.oop.state.AlarmModeAlarmState;
import ru.sbt.mipt.oop.state.DeactivatedAlarmState;
import ru.sbt.mipt.oop.type.EventType;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SignalizationEventHandlerTest {
    private EventHandler eventHandler;
    private SensorEvent sensorEvent;
    private SmartHome smartHome;

    @BeforeEach
    void beforeEach(){
        smartHome = new SmartHome(Arrays.asList(new Room(Collections.emptyList(), Collections.emptyList(),"test room")));
        eventHandler = new SignalizationEventHandler(smartHome);
    }

    @Test
    void handleEventWithTypeAlarmActivate() {
        // given
        EventType eventType = EventType.ALARM_ACTIVATE;
        sensorEvent = new SensorEvent(eventType, "0");
        sensorEvent.setCode("1234");
        //when
        eventHandler.handle(sensorEvent);
        //then
        assertTrue(smartHome.getSignalization().isActivatedAlarmState());
    }

    @Test
    void handleEventWithTypeAlarmDeactivate() {
        //given
        EventType eventType = EventType.ALARM_DEACTIVATE;
        sensorEvent = new SensorEvent(eventType, "0");
        sensorEvent.setCode("1234");
        smartHome.getSignalization().activate("1234");
        //when
        eventHandler.handle(sensorEvent);
        //then
        assertTrue(smartHome.getSignalization().isDeactivatedAlarmState());
    }

    @Test
    void handleEventWithSwitchToAlarmMode_whereCodeIsNotCorrect() {
        //given
        EventType eventType = EventType.ALARM_DEACTIVATE;
        sensorEvent = new SensorEvent(eventType, "0");
        sensorEvent.setCode("134");
        smartHome.getSignalization().activate("1234");
        //when
        eventHandler.handle(sensorEvent);
        //then
        assertTrue(smartHome.getSignalization().isAlarmModeAlarmState());
    }
}
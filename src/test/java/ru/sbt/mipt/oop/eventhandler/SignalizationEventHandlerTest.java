package ru.sbt.mipt.oop.eventhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.Signalization;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.state.ActivatedState;
import ru.sbt.mipt.oop.state.AlarmModeState;
import ru.sbt.mipt.oop.state.DeactivatedState;
import ru.sbt.mipt.oop.type.EventType;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sbt.mipt.oop.type.EventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.type.EventType.ALARM_DEACTIVATE;

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
        eventType.setCode("1234");
        sensorEvent = new SensorEvent(eventType, "0");
        //when
        eventHandler.handle(sensorEvent);
        //then
        smartHome.execute(o -> {
            if (o instanceof Signalization) {
                Signalization signalization = (Signalization) o;
                assertEquals(ActivatedState.class, signalization.getState().getClass());
            }
        });
    }

    @Test
    void handleEventWithTypeAlarmDeactivate() {
        //given
        EventType eventType = EventType.ALARM_DEACTIVATE;
        eventType.setCode("1234");
        sensorEvent = new SensorEvent(eventType, "0");
        smartHome.execute(o -> {
            if (o instanceof Signalization) {
                Signalization signalization = (Signalization) o;
                signalization.activate("1234");
            }
        });
        //when
        eventHandler.handle(sensorEvent);
        //then
        smartHome.execute(o -> {
            if (o instanceof Signalization) {
                Signalization signalization = (Signalization) o;
                assertEquals(DeactivatedState.class, signalization.getState().getClass());
            }
        });
    }

    @Test
    void handleEventWithSwitchToAlarmMode_whereCodeIsNotCorrect() {
        //given
        EventType eventType = EventType.ALARM_DEACTIVATE;
        eventType.setCode("134");
        sensorEvent = new SensorEvent(eventType, "0");
        smartHome.execute(o -> {
            if (o instanceof Signalization) {
                Signalization signalization = (Signalization) o;
                signalization.getState().activate("1234");
            }
        });
        //when
        eventHandler.handle(sensorEvent);
        //then
        smartHome.execute(o -> {
            if (o instanceof Signalization) {
                Signalization signalization = (Signalization) o;
                assertEquals(AlarmModeState.class, signalization.getState().getClass());
            }
        });
    }
}
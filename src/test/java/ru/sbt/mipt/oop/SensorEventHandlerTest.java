package ru.sbt.mipt.oop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SensorEventHandlerTest {
	private SensorEventHandler sensorEventHandler;
	private SensorEvent sensorEvent;
	private SmartHome smartHome;

	@BeforeEach
	void beforeEach(){
		smartHome = new SmartHome(Arrays.asList(new Room(
				Arrays.asList(new Light("1", false), new Light("2", true)),
				Arrays.asList(new Door(false, "1"), new Door(true, "2")),
				"test room")));
		sensorEventHandler = new SensorEventHandler(smartHome);
	}

	@Test
	void handleEventWithTypeLightOn() {
		Light light = smartHome.getRooms().stream().flatMap(r -> r.getLights().stream()).filter(l -> l.getId() == "1").findAny().orElse(null);
		sensorEvent = new SensorEvent(SensorEventType.LIGHT_ON, "1");

		sensorEventHandler.handle(sensorEvent);
		assertTrue(light.isOn());
	}

	@Test
	void handleEventWithTypeLightOff() {
		Light light = smartHome.getRooms().stream().flatMap(r -> r.getLights().stream()).filter(l -> l.getId() == "2").findAny().orElse(null);
		sensorEvent = new SensorEvent(SensorEventType.LIGHT_OFF, "2");

		sensorEventHandler.handle(sensorEvent);
		assertFalse(light.isOn());
	}

	@Test
	void handleEventWithTypeDoorOpen() {
		Door door = smartHome.getRooms().stream().flatMap(r -> r.getDoors().stream()).filter(l -> l.getId() == "1").findAny().orElse(null);
		sensorEvent = new SensorEvent(SensorEventType.DOOR_OPEN, "1");

		sensorEventHandler.handle(sensorEvent);
		assertTrue(door.isOpen());
	}

	@Test
	void handleEventWithTypeDoorClosed() {
		Door door = smartHome.getRooms().stream().flatMap(r -> r.getDoors().stream()).filter(l -> l.getId() == "2").findAny().orElse(null);
		sensorEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, "2");

		sensorEventHandler.handle(sensorEvent);
		assertFalse(door.isOpen());
	}

}
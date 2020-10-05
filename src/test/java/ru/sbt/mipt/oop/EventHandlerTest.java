package ru.sbt.mipt.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.eventhandler.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.eventhandler.LightEventHandler;
import ru.sbt.mipt.oop.light.Light;
import ru.sbt.mipt.oop.type.EventType;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventHandlerTest {
	private List<EventHandler> eventHandlers;
	private Event event;
	private SmartHome smartHome;

	@BeforeEach
	void beforeEach(){
		smartHome = new SmartHome(Arrays.asList(new Room(
				Arrays.asList(new Light("1", false), new Light("2", true)),
				Arrays.asList(new Door(false, "1"), new Door(true, "2")),
				"test room")));

		eventHandlers = Arrays.asList(new DoorEventHandler(smartHome), new LightEventHandler(smartHome));
	}

	@Test
	void handleEventWithTypeLightOn() {
		Light light = smartHome.getRooms().stream().flatMap(r -> r.getLights().stream()).filter(l -> l.getId().equals("1")).findAny().orElse(null);
		event = new Event(EventType.LIGHT_ON, "1");

		for (EventHandler eventHandler : eventHandlers) {
			eventHandler.handle(event);
		}
		assertTrue(light.isOn());
	}

	@Test
	void handleEventWithTypeLightOff() {
		Light light = smartHome.getRooms().stream().flatMap(r -> r.getLights().stream()).filter(l -> l.getId().equals("2")).findAny().orElse(null);
		event = new Event(EventType.LIGHT_OFF, "2");

		for (EventHandler eventHandler : eventHandlers) {
			eventHandler.handle(event);
		}
		assertFalse(light.isOn());
	}

	@Test
	void handleEventWithTypeDoorOpen() {
		Door door = smartHome.getRooms().stream().flatMap(r -> r.getDoors().stream()).filter(l -> l.getId().equals("1")).findAny().orElse(null);
		event = new Event(EventType.DOOR_OPEN, "1");

		for (EventHandler eventHandler : eventHandlers) {
			eventHandler.handle(event);
		}
		assertTrue(door.isOpen());
	}

	@Test
	void handleEventWithTypeDoorClosed() {
		Door door = smartHome.getRooms().stream().flatMap(r -> r.getDoors().stream()).filter(l -> l.getId().equals("2")).findAny().orElse(null);
		event = new Event(EventType.DOOR_CLOSED, "2");

		for (EventHandler eventHandler : eventHandlers) {
			eventHandler.handle(event);
		}
		assertFalse(door.isOpen());
	}

}
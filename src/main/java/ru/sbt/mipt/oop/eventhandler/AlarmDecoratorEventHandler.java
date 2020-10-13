package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Door;

import static ru.sbt.mipt.oop.type.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.type.EventType.DOOR_OPEN;

public class AlarmDecoratorEventHandler implements EventHandler {
	private final EventHandler eventHandler;

	public AlarmDecoratorEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	@Override
	public void handle(SensorEvent sensorEvent) {
	}
}

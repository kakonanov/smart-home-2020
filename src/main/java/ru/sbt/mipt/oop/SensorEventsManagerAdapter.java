package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.type.EventType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SensorEventsManagerAdapter {
	private final Collection<EventHandler> eventHandlers;
	private final SensorEventsManager sensorEventsManager;
	SensorEventsManagerAdapter(Collection<EventHandler> eventHandlers) {
		this.eventHandlers = eventHandlers;
		sensorEventsManager = new SensorEventsManager();
	}

	public SensorEventsManager getSensorEventsManager() {
		eventHandlers.forEach(eventHandler -> sensorEventsManager.registerEventHandler(event -> {
			eventHandler.handle(new SensorEvent(getEventTypeByString(event.getEventType()), event.getObjectId()));
		}));
		return sensorEventsManager;
	}

	private EventType getEventTypeByString(String CSSEventType) {
		switch (CSSEventType) {
			case "LightIsOn": return EventType.LIGHT_ON;
			case "LightIsOff": return EventType.LIGHT_OFF;
			case "DoorIsOpen": return EventType.DOOR_OPEN;
			case "DoorIsClosed": return EventType.DOOR_CLOSED;
			case "DoorIsLocked": return EventType.ALARM_ACTIVATE;
			case "DoorIsUnlocked": return EventType.ALARM_DEACTIVATE;
			default: return null;
		}
	}
}

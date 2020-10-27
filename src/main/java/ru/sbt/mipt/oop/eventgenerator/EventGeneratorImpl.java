package ru.sbt.mipt.oop.eventgenerator;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.type.EventType;

public class EventGeneratorImpl implements EventGenerator {

	public SensorEvent getNextEvent() {
		if (Math.random() < 0.05) return null;
		EventType eventType = EventType.values()[(int) (6 * Math.random())];
		String objectId = "" + ((int) (10 * Math.random()));
		SensorEvent sensorEvent = new SensorEvent(eventType, objectId);
		sensorEvent.setCode("code");
		return sensorEvent;
	}
}

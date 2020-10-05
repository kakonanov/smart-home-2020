package ru.sbt.mipt.oop.eventgenerator;

import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.eventgenerator.EventGenerator;
import ru.sbt.mipt.oop.type.EventType;

public class EventGeneratorImpl implements EventGenerator {

	public Event getNextEvent() {
		if (Math.random() < 0.05) return null;
		EventType eventType = EventType.values()[(int) (4 * Math.random())];
		String objectId = "" + ((int) (10 * Math.random()));
		return new Event(eventType, objectId);
	}
}

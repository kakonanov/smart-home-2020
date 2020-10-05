package ru.sbt.mipt.oop.eventcircle;

import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventhandler.DoorEventHandler;
import ru.sbt.mipt.oop.eventgenerator.EventGenerator;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.eventhandler.LightEventHandler;

import java.util.Arrays;
import java.util.List;

public class EventCircleImpl implements EventCircle{
	private final SmartHome smartHome;
	private final EventGenerator eventGenerator;
	private final List<EventHandler> eventHandlers;

	public EventCircleImpl(SmartHome smartHome, EventGenerator eventGenerator) {
		this.smartHome = smartHome;
		this.eventGenerator = eventGenerator;
		eventHandlers = Arrays.asList(new DoorEventHandler(smartHome), new LightEventHandler(smartHome));
	}

	public void run() {
		Event event = eventGenerator.getNextEvent();

		while (event != null) {
			for (EventHandler eventHandler : eventHandlers) {
				eventHandler.handle(event);
			}
			event = eventGenerator.getNextEvent();
		}
	}
}

package ru.sbt.mipt.oop.eventcircle;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventhandler.AllLightEventHandler;
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
		eventHandlers = Arrays.asList(new DoorEventHandler(smartHome), new LightEventHandler(smartHome), new AllLightEventHandler(smartHome));
	}

	public void run() {
		SensorEvent sensorEvent = eventGenerator.getNextEvent();

		while (sensorEvent != null) {
			for (EventHandler eventHandler : eventHandlers) {
				eventHandler.handle(sensorEvent);
			}
			sensorEvent = eventGenerator.getNextEvent();
		}
	}
}

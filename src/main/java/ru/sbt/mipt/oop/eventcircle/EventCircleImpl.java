package ru.sbt.mipt.oop.eventcircle;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventhandler.*;
import ru.sbt.mipt.oop.eventgenerator.EventGenerator;

import java.util.Arrays;
import java.util.List;

public class EventCircleImpl implements EventCircle{
	private final SmartHome smartHome;
	private final EventGenerator eventGenerator;
	private final List<EventHandler> eventHandlers;

	public EventCircleImpl(SmartHome smartHome, EventGenerator eventGenerator) {
		this.smartHome = smartHome;
		this.eventGenerator = eventGenerator;
		eventHandlers = Arrays.asList(new DoorEventHandler(smartHome), new LightEventHandler(smartHome), new AllLightEventHandler(smartHome), new SignalizationEventHandler(smartHome));
	}

	public void run() {
		SensorEvent sensorEvent = eventGenerator.getNextEvent();

		while (sensorEvent != null) {
			System.out.println(sensorEvent);
			for (EventHandler eventHandler : eventHandlers) {
				eventHandler.handle(sensorEvent);
			}
			sensorEvent = eventGenerator.getNextEvent();
		}
	}
}

package ru.sbt.mipt.oop.eventcircle;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventhandler.*;
import ru.sbt.mipt.oop.eventgenerator.EventGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventCircleImpl implements EventCircle{
	private final SmartHome smartHome;
	private final EventGenerator eventGenerator;
	private final List<EventHandler> eventHandlers;

	public EventCircleImpl(SmartHome smartHome, EventGenerator eventGenerator) {
		this.smartHome = smartHome;
		this.eventGenerator = eventGenerator;
		eventHandlers = Stream.of(new DoorEventHandler(smartHome), new LightEventHandler(smartHome), new AllLightEventHandler(smartHome), new SignalizationEventHandler(smartHome))
				.map(eventHandler -> new AlarmDecoratorEventHandler(eventHandler, smartHome)).collect(Collectors.toList());
	}

	public void run() {
		SensorEvent sensorEvent = eventGenerator.getNextEvent();

		while (sensorEvent != null) {
			System.out.println(sensorEvent);
			try {
				for (EventHandler eventHandler : eventHandlers) {
					eventHandler.handle(sensorEvent);
				}
			} catch (RuntimeException runtimeException) {
				if (runtimeException.getCause() instanceof IllegalAccessException) {
				} else {
					throw runtimeException;
				}
			}
			sensorEvent = eventGenerator.getNextEvent();
		}
	}
}

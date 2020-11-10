package ru.sbt.mipt.oop.eventcircle;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SenderAlarmMessage;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.eventhandler.*;
import ru.sbt.mipt.oop.eventgenerator.EventGenerator;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventCircleImpl implements EventCircle{
	private final EventGenerator eventGenerator;
	private final Collection<EventHandler> eventHandlers;

	public EventCircleImpl(EventGenerator eventGenerator, Collection<EventHandler> eventHandlers) {
		this.eventGenerator = eventGenerator;
		this.eventHandlers = eventHandlers;
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

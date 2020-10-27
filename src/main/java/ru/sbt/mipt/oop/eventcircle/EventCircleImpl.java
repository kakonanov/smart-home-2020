package ru.sbt.mipt.oop.eventcircle;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SenderAlarmMessage;
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
		SenderAlarmMessage senderAlarmMessage = new SenderAlarmMessage();
		eventHandlers = Stream.of(new StartEventHandler(), new DoorEventHandler(smartHome), new LightEventHandler(smartHome), new AllLightEventHandler(smartHome), new SignalizationEventHandler(smartHome))
				.map(eventHandler -> new AlarmDecoratorEventHandler(eventHandler, smartHome, senderAlarmMessage)).collect(Collectors.toList());
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

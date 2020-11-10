package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.factory.ConversionFactory;
import ru.sbt.mipt.oop.type.EventType;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class SensorEventsManagerAdapter {
	private final Collection<EventHandler> eventHandlers;
	private final ConversionFactory conversionFactory;

	@Autowired
	SensorEventsManagerAdapter(Collection<EventHandler> eventHandlers, ConversionFactory conversionFactory) {
		this.eventHandlers = eventHandlers;
		this.conversionFactory = conversionFactory;
	}

	public SensorEventsManager getSensorEventsManager() {
		SensorEventsManager sensorEventsManager = new SensorEventsManager();
		eventHandlers.forEach(eventHandler -> sensorEventsManager.registerEventHandler(event ->
			eventHandler.handle(conversionFactory.createSensorEvent(event))
		));
		return sensorEventsManager;
	}
}

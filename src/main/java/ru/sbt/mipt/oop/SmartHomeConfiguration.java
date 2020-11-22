package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import ru.sbt.mipt.oop.eventhandler.*;
import ru.sbt.mipt.oop.factory.ConversionFactory;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReaderFromJs;
import ru.sbt.mipt.oop.type.EventType;

import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan("ru.sbt.mipt.oop")
public class SmartHomeConfiguration {
	@Bean
	SmartHome smartHome() {
		return smartHomeReader().readFrom("smart-home-1.js");
	}

	@Bean
	SmartHomeReader smartHomeReader() {
		return new SmartHomeReaderFromJs();
	}

	@Bean
	SenderAlarmMessage senderAlarmMessage() {
		return new SenderAlarmMessage();
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	EventHandlerAdapter startEventHandlerAdapter(SmartHome smartHome, SenderAlarmMessage senderAlarmMessage, ConversionFactory conversionFactory) {
		return new EventHandlerAdapter(new AlarmDecoratorEventHandler(new StartEventHandler(), smartHome, senderAlarmMessage), conversionFactory);
	}

	@Bean
	@Order(3)
	EventHandlerAdapter doorEventHandlerAdapter(SmartHome smartHome, SenderAlarmMessage senderAlarmMessage, ConversionFactory conversionFactory) {
		return new EventHandlerAdapter(new AlarmDecoratorEventHandler(new DoorEventHandler(smartHome), smartHome, senderAlarmMessage), conversionFactory);
	}

	@Bean
	@Order(1)
	EventHandlerAdapter lightEventHandlerAdapter(SmartHome smartHome, SenderAlarmMessage senderAlarmMessage, ConversionFactory conversionFactory) {
		return new EventHandlerAdapter(new AlarmDecoratorEventHandler(new LightEventHandler(smartHome), smartHome, senderAlarmMessage), conversionFactory);

	}

	@Bean
	@Order(2)
	EventHandlerAdapter allLightEventHandlerAdapter(SmartHome smartHome, SenderAlarmMessage senderAlarmMessage, ConversionFactory conversionFactory) {
		return new EventHandlerAdapter(new AlarmDecoratorEventHandler(new AllLightEventHandler(smartHome), smartHome, senderAlarmMessage), conversionFactory);
	}

	@Bean
	@Order(0)
	EventHandlerAdapter signalizationEventHandlerAdapter(SmartHome smartHome, SenderAlarmMessage senderAlarmMessage, ConversionFactory conversionFactory) {
		return new EventHandlerAdapter(new AlarmDecoratorEventHandler(new SignalizationEventHandler(smartHome), smartHome, senderAlarmMessage), conversionFactory);
	}

	@Bean
	public Map<String, EventType> stringToEventType() {
		return Map.of(
				"LightIsOn",EventType.LIGHT_ON,
				"LightIsOff", EventType.LIGHT_OFF,
				"DoorIsOpen",  EventType.DOOR_OPEN,
				"DoorIsClosed",  EventType.DOOR_CLOSED,
				"DoorIsLocked",  EventType.ALARM_ACTIVATE,
				"DoorIsUnlocked",  EventType.ALARM_DEACTIVATE);
	}

	@Bean
	SensorEventsManager sensorEventsManager(List<EventHandlerAdapter> eventHandlers) {
		SensorEventsManager sensorEventsManager = new SensorEventsManager();
		eventHandlers.forEach(sensorEventsManager::registerEventHandler);
		return sensorEventsManager;
	}
}

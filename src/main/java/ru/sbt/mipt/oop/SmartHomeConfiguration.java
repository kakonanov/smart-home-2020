package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import ru.sbt.mipt.oop.eventhandler.*;
import ru.sbt.mipt.oop.factory.ConversionFactory;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReaderFromJs;
import ru.sbt.mipt.oop.type.EventType;

import javax.annotation.Resource;
import java.util.Collection;
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
	EventHandler startEventHandler() {
		return new AlarmDecoratorEventHandler(new StartEventHandler(), smartHome(), senderAlarmMessage());
	}

	@Bean
	@Order()
	EventHandler doorEventHandler() {
		return new AlarmDecoratorEventHandler(new DoorEventHandler(smartHome()), smartHome(), senderAlarmMessage());
	}

	@Bean
	@Order()
	EventHandler lightEventHandler() {
		return new AlarmDecoratorEventHandler(new LightEventHandler(smartHome()), smartHome(), senderAlarmMessage());
	}

	@Bean
	@Order()
	EventHandler allLightEventHandler() {
		return new AlarmDecoratorEventHandler(new AllLightEventHandler(smartHome()), smartHome(), senderAlarmMessage());
	}

	@Bean
	@Order()
	EventHandler signalizationEventHandler() {
		return new AlarmDecoratorEventHandler(new SignalizationEventHandler(smartHome()), smartHome(), senderAlarmMessage());
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
	SensorEventsManager sensorEventsManager(Collection<EventHandler> eventHandlers, ConversionFactory conversionFactory) {
		return new SensorEventsManagerAdapter(eventHandlers, conversionFactory).getSensorEventsManager();
	}
}

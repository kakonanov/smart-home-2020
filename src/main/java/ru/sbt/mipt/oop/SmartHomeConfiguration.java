package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.eventhandler.*;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReaderFromJs;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	Collection<EventHandler> eventHandlers() {
		SenderAlarmMessage senderAlarmMessage = new SenderAlarmMessage();
		SmartHome smartHome = smartHome();
		return Stream.of(new StartEventHandler(), new DoorEventHandler(smartHome), new LightEventHandler(smartHome), new AllLightEventHandler(smartHome), new SignalizationEventHandler(smartHome))
				.map(eventHandler -> new AlarmDecoratorEventHandler(eventHandler, smartHome, senderAlarmMessage)).collect(Collectors.toList());
	}

	@Bean
	SensorEventsManager sensorEventsManager() {
		return new SensorEventsManagerAdapter(eventHandlers()).getSensorEventsManager();
	}
}

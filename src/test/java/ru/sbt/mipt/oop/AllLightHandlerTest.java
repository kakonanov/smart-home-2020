package ru.sbt.mipt.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllLightHandlerTest {
	SmartHome smartHome;
	AllLightHandler allLightHandler;

	@BeforeEach
	void beforeEach(){
		smartHome = SmartHomeCreator.getSmartHome();
		allLightHandler = new AllLightHandler(smartHome);
	}

	@Test
	void turnOff() {
		Light light = smartHome.getRooms().stream().flatMap(r -> r.getLights().stream()).findAny().orElse(null);
		if (light == null) return;
		new LightHandler(light).turnOn();

		allLightHandler.turnOff();
		assertFalse(smartHome.getRooms().stream().flatMap(r -> r.getLights().stream()).anyMatch(Light::isOn));
	}
}
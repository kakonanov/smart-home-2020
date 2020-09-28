package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LightHandlerTest {

	@Test
	void turnOn() {
		Light light = new Light("1", false);
		LightHandler lightHandler = new LightHandler(light);
		lightHandler.turnOn();
		assertTrue(light.isOn());
	}

	@Test
	void turnOff() {
		Light light = new Light("1", true);
		LightHandler lightHandler = new LightHandler(light);
		lightHandler.turnOff();
		assertFalse(light.isOn());
	}
}
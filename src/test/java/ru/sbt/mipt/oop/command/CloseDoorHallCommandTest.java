package ru.sbt.mipt.oop.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.eventhandler.EventHandler;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CloseDoorHallCommandTest {
	private EventHandler eventHandler;
	private SensorEvent sensorEvent;
	private SmartHome smartHome;
	private Door door1;
	private Door door2;
	private Door door3;
	private Door door4;
	private Light light1;
	private Light light2;

	@BeforeEach
	void beforeEach() {
		light1 = new Light("1", false);
		light2 = new Light("2", true);
		door1 = new Door(false, "1");
		door2 = new Door(true, "2");
		door3 = new Door(false, "3");
		door4 = new Door(true, "4");
		smartHome = new SmartHome(Arrays.asList(
				new Room(
						Arrays.asList(light1, light2),
						Arrays.asList(door1, door2),
						"hall"),
				new Room(
						Collections.emptyList(),
						Arrays.asList(door3, door4),
						"notHall")));
	}

	@Test
	void execute() {
		// given
		Command command = new CloseDoorHallCommand(smartHome);
		// when
		command.execute();
		// than
		assertAll(
				() -> assertFalse(light1.isOn()),
				() -> assertTrue(light2.isOn()),
				() -> assertFalse(door1.isOpen()),
				() -> assertFalse(door2.isOpen()),
				() -> assertFalse(door3.isOpen()),
				() -> assertTrue(door4.isOpen())
		);
	}
}
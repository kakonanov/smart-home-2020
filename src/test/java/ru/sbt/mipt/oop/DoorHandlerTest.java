package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorHandlerTest {

	@Test
	void open() {
		Door door = new Door(false, "1");
		DoorHandler doorHandler = new DoorHandler(door);
		doorHandler.open();
		assertTrue(door.isOpen());
	}

	@Test
	void close() {
		Door door = new Door(true, "1");
		DoorHandler doorHandler = new DoorHandler(door);
		doorHandler.close();
		assertFalse(door.isOpen());
	}
}
package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Door;

import static ru.sbt.mipt.oop.type.EventType.*;

public class DoorEventHandler implements EventHandler {
	private final SmartHome smartHome;

	public DoorEventHandler(SmartHome smartHome) {
		this.smartHome = smartHome;
	}

	@Override
	public void handle(SensorEvent sensorEvent) {
		if (sensorEvent.getType().toString().contains("DOOR_")) {
			smartHome.execute(o -> {
				if (o instanceof Door) {
					Door door = (Door) o;
					if (door.getId().equals(sensorEvent.getObjectId())) {
						if (sensorEvent.getType() == DOOR_OPEN) {
							doorOpen(door);
						} else if (sensorEvent.getType() == DOOR_CLOSED) {
							doorClosed(door);
						}
					}
				}
			});
		}
	}

	private void doorOpen(Door door) {
		door.setOpen(true);
		System.out.println("Door " + door.getId() + " was opened.");
	}

	private void doorClosed(Door door) {
		door.setOpen(false);
		System.out.println("Door " + door.getId() + " was closed.");
	}
}

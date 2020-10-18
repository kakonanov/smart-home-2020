package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commandsender.CommandSenderImpl;
import ru.sbt.mipt.oop.door.Door;
import ru.sbt.mipt.oop.light.Light;
import ru.sbt.mipt.oop.type.CommandType;

import static ru.sbt.mipt.oop.type.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.type.EventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {
	private final SmartHome smartHome;
	private final CommandSenderImpl commandSender;

	public DoorEventHandler(SmartHome smartHome) {
		this.smartHome = smartHome;
		this.commandSender = new CommandSenderImpl();
	}

	public void handle(Event event) {
		for (Room room : smartHome.getRooms()) {
			for (Door door : room.getDoors()) {
				if (door.getId().equals(event.getObjectId())) {
					if (event.getType() == DOOR_OPEN) {
						doorOpen(room, door);
					} else if (event.getType() == DOOR_CLOSED) {
						doorClosed(room, door);
					}
				}
			}
		}
	}

	private void doorOpen(Room room, Door door) {
		door.setOpen(true);
		System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
	}

	private void doorClosed(Room room, Door door) {
		door.setOpen(false);
		System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
	}
}

package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Room;

public class CloseDoorHallCommand implements Command{
	private final SmartHome smartHome;

	public CloseDoorHallCommand(SmartHome smartHome) {
		this.smartHome =smartHome;
	}

	@Override
	public boolean execute() {
		smartHome.execute(o -> {
			if (o instanceof Room) {
				Room room = (Room) o;
				if (room.getName().equals("hall")) {
					room.execute(d -> {
						if (d instanceof Door) {
							Door door = (Door) d;
							doorClosed(door);
						}
					});
				}
			}
		});
		return true;
	}

	private void doorClosed(Door door) {
		door.setOpen(false);
		System.out.println("Door " + door.getId() + " was closed.");
	}
}

package ru.sbt.mipt.oop.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.type.EventType;

import javax.annotation.Resource;
import java.util.Collection;

@Component
public class CloseDoorHallCommand implements Command{
	private final SmartHome smartHome;

	@Autowired
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

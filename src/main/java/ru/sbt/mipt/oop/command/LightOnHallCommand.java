package ru.sbt.mipt.oop.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;

@Component
public class LightOnHallCommand implements Command{
	private final SmartHome smartHome;

	@Autowired
	public LightOnHallCommand(SmartHome smartHome) {
		this.smartHome =smartHome;
	}

	@Override
	public boolean execute() {
		smartHome.execute(o -> {
			if (o instanceof Room) {
				Room room = (Room) o;
				if (room.getName().equals("hall")) {
					room.execute(l -> {
						if (l instanceof Light) {
							Light light = (Light) l;
							lightOn(light);
						}
					});
				}
			}
		});
		return true;
	}

	private void lightOn(Light light) {
		light.setOn(true);
		System.out.println("Light " + light.getId() + " was turned on.");
	}
}

package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;

public class LightOffAllHomeCommand implements Command{
	private final SmartHome smartHome;

	public LightOffAllHomeCommand(SmartHome smartHome) {
		this.smartHome =smartHome;
	}

	@Override
	public boolean execute() {
		smartHome.execute(o -> {
			if (o instanceof Room) {
				Room room = (Room) o;
				room.execute(l -> {
					if (l instanceof Light) {
						Light light = (Light) l;
						lightOff(light);
					}
				});
			}
		});
		return true;
	}

	private void lightOff(Light light) {
		light.setOn(false);
		System.out.println("Light " + light.getId() + " was turned off.");
	}
}

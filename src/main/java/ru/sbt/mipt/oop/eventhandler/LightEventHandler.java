package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.light.Light;

import static ru.sbt.mipt.oop.type.EventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.type.EventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
	private final SmartHome smartHome;

	public LightEventHandler(SmartHome smartHome) {
		this.smartHome = smartHome;
	}

	public void handle(Event event) {
		for (Room room : smartHome.getRooms()) {
			for (Light light : room.getLights()) {
				if (light.getId().equals(event.getObjectId())) {
					if (event.getType() == LIGHT_ON) {
						lightOn(room, light);
					} else if (event.getType() == LIGHT_OFF) {
						lightOff(room, light);
					}
				}
			}
		}
	}

	private void lightOn(Room room, Light light) {
		light.setOn(true);
		System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
	}

	private void lightOff(Room room, Light light) {
		light.setOn(false);
		System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
	}
}

package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler {
	private final SmartHome smartHome;

	LightEventHandler(SmartHome smartHome) {
		this.smartHome = smartHome;
	}

	public void handle(SensorEvent sensorEvent) {
		for (Room room : smartHome.getRooms()) {
			for (Light light : room.getLights()) {
				if (light.getId().equals(sensorEvent.getObjectId())) {
					LightHandler lightHandler = new LightHandler(light);
					if (sensorEvent.getType() == LIGHT_ON) {
						lightHandler.turnOn();
						System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
					} else {
						lightHandler.turnOff();
						System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
					}
				}
			}
		}
	}
}

package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler {
	private final SmartHome smartHome;

	DoorEventHandler(SmartHome smartHome) {
		this.smartHome = smartHome;
	}

	public void handle(SensorEvent sensorEvent) {
		for (Room room : smartHome.getRooms()) {
			for (Door door : room.getDoors()) {
				if (door.getId().equals(sensorEvent.getObjectId())) {
					DoorHandler doorHandler = new DoorHandler(door);
					if (sensorEvent.getType() == DOOR_OPEN) {
						doorHandler.open();
						System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
					} else {
						doorHandler.close();
						System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
						// если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
						// в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
						if (room.getName().equals("hall")) {
							new AllLightHandler(smartHome).turnOff();
						}
					}
				}
			}
		}
	}
}

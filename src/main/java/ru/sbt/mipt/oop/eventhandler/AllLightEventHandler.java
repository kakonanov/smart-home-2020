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

public class AllLightEventHandler implements EventHandler {
	private final SmartHome smartHome;
	private final CommandSenderImpl commandSender;

	public AllLightEventHandler(SmartHome smartHome) {
		this.smartHome = smartHome;
		this.commandSender = new CommandSenderImpl();
	}

	public void handle(Event event) {
		for (Room room : smartHome.getRooms()) {
			for (Door door : room.getDoors()) {
				if (door.getId().equals(event.getObjectId())) {
					if (event.getType() == DOOR_CLOSED && room.getName().equals("hall")) {
						turnOffAllLights();
					}
				}
			}
		}
	}

	private void turnOffAllLights() {
		for (Room room : smartHome.getRooms()) {
			for (Light light : room.getLights()) {
				light.setOn(false);
				SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
				commandSender.sendCommand(command);
			}
		}
	}
}

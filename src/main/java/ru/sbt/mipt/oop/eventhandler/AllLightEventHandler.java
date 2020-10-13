package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commandsender.CommandSenderImpl;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.type.CommandType;

import static ru.sbt.mipt.oop.type.EventType.*;

public class AllLightEventHandler implements EventHandler {
	private final SmartHome smartHome;
	private final CommandSenderImpl commandSender;

	public AllLightEventHandler(SmartHome smartHome) {
		this.smartHome = smartHome;
		this.commandSender = new CommandSenderImpl();
	}

	@Override
	public void handle(SensorEvent sensorEvent) {
		if (sensorEvent.getType() == DOOR_CLOSED) {
			smartHome.execute(o -> {
				if (o instanceof Room) {
					Room room = (Room) o;
					if (room.getName().equals("hall") && room.containsDoor(sensorEvent.getObjectId())) {
						smartHome.execute(a -> {
							if (a instanceof Light) {
								Light light = (Light) a;
								lightOff(light);
							}
						});
					}
				}
			});
		}
	}

	private void lightOff(Light light) {
		light.setOn(false);
		SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
		commandSender.sendCommand(command);
	}
}
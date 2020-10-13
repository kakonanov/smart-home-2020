package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.domain.Light;

import static ru.sbt.mipt.oop.type.EventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.type.EventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {
	private final SmartHome smartHome;

	public LightEventHandler(SmartHome smartHome) {
		this.smartHome = smartHome;
	}

	@Override
	public void handle(SensorEvent sensorEvent) {
		if (sensorEvent.getType().toString().contains("LIGHT_")) {
			smartHome.execute(o -> {
				if (o instanceof Light) {
					Light light = (Light) o;
					if (light.getId().equals(sensorEvent.getObjectId())) {
						if (sensorEvent.getType() == LIGHT_ON) {
							lightOn(light);
						} else if (sensorEvent.getType() == LIGHT_OFF) {
							lightOff(light);
						}
					}
				}
			});
		}
	}

	private void lightOn(Light light) {
		light.setOn(true);
		System.out.println("Light " + light.getId() + " was turned on.");
	}

	private void lightOff(Light light) {
		light.setOn(false);
		System.out.println("Light " + light.getId() + " was turned off.");
	}
}

package ru.sbt.mipt.oop.eventhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.Signalization;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Light;

import static ru.sbt.mipt.oop.type.EventType.*;

@Component
public class SignalizationEventHandler implements EventHandler {
	private final SmartHome smartHome;

	@Autowired
	public SignalizationEventHandler(SmartHome smartHome) {
		this.smartHome = smartHome;
	}

	@Override
	public void handle(SensorEvent sensorEvent) {
		if (sensorEvent.getType().toString().contains("ALARM_")) {
			Signalization signalization = smartHome.getSignalization();
			String code = sensorEvent.getCode();
			if (sensorEvent.getType() == ALARM_ACTIVATE) {
				signalization.getState().activate(code);
			} else if (sensorEvent.getType() == ALARM_DEACTIVATE) {
				signalization.getState().deactivate(code);
			}
		}
	}

}

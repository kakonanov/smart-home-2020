package ru.sbt.mipt.oop.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.Signalization;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Light;
import ru.sbt.mipt.oop.domain.Room;
import ru.sbt.mipt.oop.state.ActivatedAlarmState;

import static ru.sbt.mipt.oop.type.EventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.type.EventType.ALARM_DEACTIVATE;

@Component
public class ActivateSignalizationCommand implements Command{
	private final SmartHome smartHome;

	@Autowired
	public ActivateSignalizationCommand(SmartHome smartHome) {
		this.smartHome =smartHome;
	}

	@Override
	public boolean execute() {
		Signalization signalization = smartHome.getSignalization();
		signalization.activate("code");
		return true;
	}
}

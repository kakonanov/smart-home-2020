package ru.sbt.mipt.oop.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.Signalization;
import ru.sbt.mipt.oop.SmartHome;

@Component
public class AlarmModeSignalizationCommand implements Command{
	private final SmartHome smartHome;

	@Autowired
	public AlarmModeSignalizationCommand(SmartHome smartHome) {
		this.smartHome =smartHome;
	}

	@Override
	public boolean execute() {
		Signalization signalization = smartHome.getSignalization();
		signalization.switchToAlarmMode();
		return true;
	}
}

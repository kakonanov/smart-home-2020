package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.Signalization;
import ru.sbt.mipt.oop.SmartHome;

public class AlarmModeSignalizationCommand implements Command{
	private final SmartHome smartHome;

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

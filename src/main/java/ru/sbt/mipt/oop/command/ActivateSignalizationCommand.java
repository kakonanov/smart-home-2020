package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.Signalization;
import ru.sbt.mipt.oop.SmartHome;

public class ActivateSignalizationCommand implements Command{
	private final SmartHome smartHome;

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

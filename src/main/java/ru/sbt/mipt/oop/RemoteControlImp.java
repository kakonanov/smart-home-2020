package ru.sbt.mipt.oop;

import rc.RemoteControl;
import ru.sbt.mipt.oop.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RemoteControlImp implements RemoteControl {
	private final String rcId;
	private final Map<String, Command> buttons = new HashMap<>();
	private final Set<String> availableButtonCodes = Set.of("A", "B", "C", "D", "1", "2", "3", "4");

	public RemoteControlImp(String rcId) {
		this.rcId = rcId;
	}

	public Map<String, Command> getButtons() {
		return buttons;
	}

	public void setButton(String buttonCode, Command command) {
		if (availableButtonCodes.contains(buttonCode))
			buttons.put(buttonCode, command);
	}

	@Override
	public void onButtonPressed(String buttonCode, String rcId) {
		if (Objects.equals(this.rcId, rcId)) {
			if (buttons.containsKey(buttonCode)) {
				buttons.get(buttonCode).execute();
			}
		}
	}
}

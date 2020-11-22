package ru.sbt.mipt.oop;

import rc.RemoteControl;
import ru.sbt.mipt.oop.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RemoteControlImp implements RemoteControl {
	private final String rcId;
	private final Map<String, Command> buttons;

	public RemoteControlImp(String rcId, Map<String, Command> buttons) {
		this.rcId = rcId;
		this.buttons = buttons;
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

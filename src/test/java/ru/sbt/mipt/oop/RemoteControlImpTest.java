package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import rc.RemoteControl;
import ru.sbt.mipt.oop.command.Command;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RemoteControlImpTest {
	Command command = () -> {
		System.out.println("command execute");
		return true;
	};

	@Test
	void onButtonPressed_whenButtonCodeExist() {
		// given
		RemoteControlImp remoteControlImp = new RemoteControlImp("1", Map.of("A", command));
		// when
		remoteControlImp.onButtonPressed("A", "1");
		// than

	}

	@Test
	void onButtonPressed_whenButtonCodeDoesNotExist() {
		// given
		RemoteControlImp remoteControlImp = new RemoteControlImp("1", Map.of("A", command));
		// when
		remoteControlImp.onButtonPressed("C", "1");
		// than

	}
}
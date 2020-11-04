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
	void setButton_whenButtonCodeIsCorrect() {
		// given
		RemoteControlImp remoteControlImp = new RemoteControlImp("1");
		// when
		remoteControlImp.setButton("A", command);
		// than
		assertEquals(Map.of("A", command), remoteControlImp.getButtons());
	}

	@Test
	void setButton_whenButtonCodeIsNotCorrect() {
		// given
		RemoteControlImp remoteControlImp = new RemoteControlImp("1");
		// when
		remoteControlImp.setButton("Z", command);
		// than
		assertEquals(Collections.emptyMap(), remoteControlImp.getButtons());
	}

	@Test
	void onButtonPressed_whenButtonCodeExist() {
		// given
		RemoteControlImp remoteControlImp = new RemoteControlImp("1");
		remoteControlImp.setButton("A", command);
		// when
		remoteControlImp.onButtonPressed("A", "1");
		// than

	}

	@Test
	void onButtonPressed_whenButtonCodeDoesNotExist() {
		// given
		RemoteControlImp remoteControlImp = new RemoteControlImp("1");
		remoteControlImp.setButton("A", command);
		// when
		remoteControlImp.onButtonPressed("C", "1");
		// than

	}
}
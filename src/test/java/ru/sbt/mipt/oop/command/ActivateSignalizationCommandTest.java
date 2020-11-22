package ru.sbt.mipt.oop.command;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.Signalization;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.state.ActivatedAlarmState;

import static org.junit.jupiter.api.Assertions.*;

class ActivateSignalizationCommandTest {

	@Test
	void execute() {
		// given
		SmartHome smartHome = new SmartHome();
		Signalization signalization = smartHome.getSignalization();
		Command command = new ActivateSignalizationCommand(smartHome);
		// when
		command.execute();
		// than
		assertTrue(signalization.isActivatedAlarmState());
	}
}
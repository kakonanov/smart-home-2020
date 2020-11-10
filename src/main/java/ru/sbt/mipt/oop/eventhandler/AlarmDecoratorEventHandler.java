package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.SenderAlarmMessage;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.Signalization;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.state.ActivatedAlarmState;
import ru.sbt.mipt.oop.state.AlarmModeAlarmState;

import static ru.sbt.mipt.oop.type.EventType.*;

public class AlarmDecoratorEventHandler implements EventHandler {
	private final EventHandler eventHandler;
	private final SmartHome smartHome;
	private final SenderAlarmMessage senderAlarmMessage;

	public AlarmDecoratorEventHandler(EventHandler eventHandler, SmartHome smartHome, SenderAlarmMessage senderAlarmMessage) {
		this.eventHandler = eventHandler;
		this.smartHome = smartHome;
		this.senderAlarmMessage = senderAlarmMessage;
	}

	@Override
	public void handle(SensorEvent sensorEvent) {
		Signalization signalization = smartHome.getSignalization();
		if (sensorEvent.getType() == ALARM_DEACTIVATE) {
			eventHandler.handle(sensorEvent);
		} else if (eventHandler instanceof StartEventHandler) {
			senderAlarmMessage.setFlag(true);
		}
		else if (signalization.isAlarmModeAlarmState()) {
			senderAlarmMessage.sendMessage("Sending sms - alarm mode");
		}
		else if (signalization.isActivatedAlarmState()) {
			System.out.println("Sending sms - event with active signalization");
			System.out.println("Sending sms - switch to alarm mode");
			signalization.setState(new AlarmModeAlarmState(signalization));
		} else {
			eventHandler.handle(sensorEvent);
		}
	}
}

package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.Signalization;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.domain.Door;
import ru.sbt.mipt.oop.state.ActivatedState;
import ru.sbt.mipt.oop.state.AlarmModeState;

import static ru.sbt.mipt.oop.type.EventType.*;

public class AlarmDecoratorEventHandler implements EventHandler {
	private final EventHandler eventHandler;
	private final SmartHome smartHome;

	public AlarmDecoratorEventHandler(EventHandler eventHandler, SmartHome smartHome) {
		this.eventHandler = eventHandler;
		this.smartHome = smartHome;
	}

	@Override
	public void handle(SensorEvent sensorEvent) {
		smartHome.execute(o -> {
			if (o instanceof Signalization) {
				Signalization signalization = (Signalization) o;
				if (signalization.getState().getClass().equals(AlarmModeState.class)) {
					System.out.println("Sending sms - alarm mode");
					throw new RuntimeException(new IllegalAccessException());
				}
				if (signalization.getState().getClass().equals(ActivatedState.class)) {
					if (sensorEvent.getType() != ALARM_DEACTIVATE) {
						System.out.println("Sending sms - event with active signalization");
						System.out.println("Sending sms - switch to alarm mode");
						signalization.setState(new AlarmModeState(signalization));
						throw new RuntimeException(new IllegalAccessException());
					}
				}
				eventHandler.handle(sensorEvent);
			}
		});
	}
}

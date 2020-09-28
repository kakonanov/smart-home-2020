package ru.sbt.mipt.oop;

public class SensorEventCicle {
	private final SmartHome smartHome;

	SensorEventCicle(SmartHome smartHome) {
		this.smartHome = smartHome;
	}
	void run() {
		SensorEventHandler sensorEventHandler = new SensorEventHandler(smartHome);
		SensorEvent sensorEvent = SensorEventGenerator.getNextSensorEvent();

		while (sensorEvent != null) {
			sensorEventHandler.handle(sensorEvent);
			sensorEvent = SensorEventGenerator.getNextSensorEvent();
		}
	}
}

package ru.sbt.mipt.oop.factory;

import com.coolcompany.smarthome.events.CCSensorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.type.EventType;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class ConversionFactory {
	@Resource
	private Map<String, EventType> stringToEventType;

	public ConversionFactory() {}

	public ConversionFactory(Map<String, EventType> stringToEventType) {
		this.stringToEventType = stringToEventType;
	}

	public SensorEvent createSensorEvent(CCSensorEvent ccSensorEvent) {
		return new SensorEvent(stringToEventType.get(ccSensorEvent.getEventType()), ccSensorEvent.getObjectId());
	}

}

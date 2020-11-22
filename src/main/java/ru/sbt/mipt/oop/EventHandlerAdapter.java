package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.factory.ConversionFactory;

public class EventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final ru.sbt.mipt.oop.eventhandler.EventHandler eventHandler;
    private final ConversionFactory conversionFactory;

    public EventHandlerAdapter(ru.sbt.mipt.oop.eventhandler.EventHandler eventHandler, ConversionFactory conversionFactory){
        this.eventHandler = eventHandler;
        this.conversionFactory = conversionFactory;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventHandler.handle(conversionFactory.createSensorEvent(event));
    }
}

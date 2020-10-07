package ru.sbt.mipt.oop.eventgenerator;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventGenerator {
    SensorEvent getNextEvent();
}

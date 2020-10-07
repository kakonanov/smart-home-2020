package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.domain.Actionable;

@FunctionalInterface
public interface Action {
    void doAction(Actionable actionable);
}

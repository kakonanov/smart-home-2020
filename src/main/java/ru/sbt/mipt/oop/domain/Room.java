package ru.sbt.mipt.oop.domain;

import ru.sbt.mipt.oop.Action;

import java.util.Collection;

public class Room implements Actionable {
    private final Collection<Light> lights;
    private final Collection<Door> doors;
    private final String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        for (Door door : doors) {
            door.execute(action);
        }
        for (Light light : lights) {
            light.execute(action);
        }
        action.doAction(this);
    }
}

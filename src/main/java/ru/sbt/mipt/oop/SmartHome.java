package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.domain.Actionable;
import ru.sbt.mipt.oop.domain.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private final Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void execute(Action action) {
        for (Room room : rooms) {
            room.execute(action);
        }
        action.doAction(this);
    }
}

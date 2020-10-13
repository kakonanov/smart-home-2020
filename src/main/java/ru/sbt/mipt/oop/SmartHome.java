package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.domain.Actionable;
import ru.sbt.mipt.oop.domain.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private final Collection<Room> rooms;
    private Signalization signalization;

    public SmartHome() {
        rooms = new ArrayList<>();
        signalization = new Signalization();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        for (Room room : rooms) {
            room.execute(action);
        }
        signalization.execute(action);
    }
}

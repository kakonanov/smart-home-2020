package ru.sbt.mipt.oop;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.domain.Actionable;
import ru.sbt.mipt.oop.domain.Room;

import java.util.ArrayList;
import java.util.Collection;

@Component("smartHome")
public class SmartHome implements Actionable {
    private final Collection<Room> rooms;
    private Signalization signalization;

    public SmartHome() {
        rooms = new ArrayList<>();
        signalization = new Signalization();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        signalization = new Signalization();
    }

    public Signalization getSignalization() {
        return signalization;
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

package ru.sbt.mipt.oop;

public class DoorHandler {
    Door door;

    DoorHandler(Door door) {
        this.door = door;
    }

    public void open() {
        door.setOpen(true);
    }

    public void close() {
        door.setOpen(false);
    }
}

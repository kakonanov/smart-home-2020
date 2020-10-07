package ru.sbt.mipt.oop.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {
    private Door door;

    @Test
    void execute() {
        //given
        door = new Door(true, "1");
        //when
        door.execute(o -> {
                if (o instanceof Door) {
                Door door = (Door) o;
                door.setOpen(false);
                System.out.println("This is " + door.getId() + " door");
            }
        });
        //then
        assertFalse(door.isOpen());
    }
}
package ru.sbt.mipt.oop.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RoomTest {
    @Test
    void execute() {
        //given
        Light light1 = new Light("1", false);
        Light light2 = new Light("2", true);
        Door door1 = new Door(false, "1");
        Door door2 = new Door(true, "2");
        Room room = new Room(
                Arrays.asList(light1, light2),
                Arrays.asList(door1, door2),
                "test room");
        //when
        room.execute(o -> {
            if (o instanceof Room) {
                Room roomTmp = (Room) o;
                System.out.println("This is \"" + roomTmp.getName() + "\" room");
            } else if (o instanceof Light) {
                Light light = (Light) o;
                light.setOn(false);
                System.out.println("This is " + light.getId() + " light");
            }
            else if (o instanceof Door) {
                Door door = (Door) o;
                door.setOpen(false);
                System.out.println("This is " + door.getId() + " door");
            }
        });
        //then
        assertAll(
                () -> assertFalse(light1.isOn()),
                () -> assertFalse(light2.isOn()),
                () -> assertFalse(door1.isOpen()),
                () -> assertFalse(door2.isOpen())
        );
    }
}
package ru.sbt.mipt.oop.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LightTest {
    private Light light;

    @Test
    void execute() {
        //given
        light = new Light("1", true);
        //when
        light.execute(o -> {
            if (o instanceof Light) {
                Light light = (Light) o;
                light.setOn(false);
                System.out.println("This is " + light.getId() + " light");
            }
        });
        //then
        assertFalse(light.isOn());
    }
}
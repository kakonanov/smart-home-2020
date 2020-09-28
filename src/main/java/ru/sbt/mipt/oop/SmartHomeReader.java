package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeReader {
    public SmartHome readFromJs(String filename) {
        try {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(Paths.get(filename)));
            return gson.fromJson(json, SmartHome.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return new SmartHome();
        }
    }
}

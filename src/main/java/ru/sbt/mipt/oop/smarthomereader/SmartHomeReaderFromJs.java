package ru.sbt.mipt.oop.smarthomereader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeReaderFromJs implements SmartHomeReader {
    private final String filename;
    public SmartHomeReaderFromJs(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome read() {
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

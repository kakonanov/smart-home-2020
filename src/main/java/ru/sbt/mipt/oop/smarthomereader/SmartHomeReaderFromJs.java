package ru.sbt.mipt.oop.smarthomereader;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component("smartHomeReader")
public class SmartHomeReaderFromJs implements SmartHomeReader {
    @Override
    public SmartHome readFrom(String filename) {
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

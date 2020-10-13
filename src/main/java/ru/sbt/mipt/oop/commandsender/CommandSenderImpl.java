package ru.sbt.mipt.oop.commandsender;

import ru.sbt.mipt.oop.SensorCommand;

public class CommandSenderImpl implements CommandSender {
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}

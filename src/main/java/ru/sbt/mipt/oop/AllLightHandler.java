package ru.sbt.mipt.oop;

public class AllLightHandler {
    private SmartHome smartHome;

    AllLightHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void turnOff() {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                new LightHandler(light).turnOff();
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}

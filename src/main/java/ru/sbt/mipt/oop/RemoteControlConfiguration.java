package ru.sbt.mipt.oop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.command.*;

import javax.annotation.Resource;
import java.util.*;

@Configuration
@Import(SmartHomeConfiguration.class)
public class RemoteControlConfiguration {
    @Resource
    private Map<String, Command> buttons;

    @Bean
    RemoteControlRegistry remoteControlRegistry(){
        return new RemoteControlRegistry();
    }

    @Bean
    RemoteControl firstRemoteControl(RemoteControlRegistry remoteControlRegistry, @Value("1") String rcId){
        RemoteControl remoteControl = new RemoteControlImp(rcId, buttons);
        remoteControlRegistry.registerRemoteControl(remoteControl, rcId);
        return remoteControl;
    }

    @Bean
    Map<String, Command> buttons(Collection<Command> commands){
        Map<String, Command> buttons = new HashMap<>();
        Deque<String> availableButtons = new ArrayDeque<>(List.of("A", "b", "C", "D", "1", "2", "3", "4"));
        for (Command command : commands){
            if (availableButtons.isEmpty()) break;
            buttons.put(availableButtons.pop(), command);
        }
        return buttons;
    }

    @Bean
    Command activateSignalizationCommand(SmartHome smartHome) {
        return new ActivateSignalizationCommand(smartHome);
    }
    @Bean
    Command alarmModeSignalizationCommand(SmartHome smartHome) {
        return new AlarmModeSignalizationCommand(smartHome);
    }
    @Bean
    Command closeDoorHallCommand(SmartHome smartHome) {
        return new CloseDoorHallCommand(smartHome);
    }
    @Bean
    Command lightOffAllHomeCommand(SmartHome smartHome) {
        return new LightOffAllHomeCommand(smartHome);
    }
    @Bean
    Command lightOnAllHomeCommand(SmartHome smartHome) {
        return new LightOnAllHomeCommand(smartHome);
    }
    @Bean
    Command lightOnHallCommand(SmartHome smartHome) {
        return new LightOnHallCommand(smartHome);
    }

}

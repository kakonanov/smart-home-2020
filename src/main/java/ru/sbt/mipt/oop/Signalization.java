package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.domain.Actionable;
import ru.sbt.mipt.oop.state.DeactivatedState;
import ru.sbt.mipt.oop.state.State;

public class Signalization implements Actionable {
    private State state;
    private String code;

    Signalization() {
        this.state = new DeactivatedState(this);
    }

    public State getState() {
        return state;
    }

    public void activate(String code) {
        this.code = code;
        System.out.println("Signalization activated");
    }

    public void deactivate(String code) {
        if (this.code.equals(code)) {
            this.code = null;
            System.out.println("Signalization deactivated");
        } else {
            state.switchToAlarmMode();
            System.out.println("Alarm!!");
        }
    }

    public void setState(State state){
        this.state = state;
    }

    @Override
    public void execute(Action action) {
        action.doAction(this);
    }
}

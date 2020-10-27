package ru.sbt.mipt.oop;

public class SenderAlarmMessage {
	private boolean flag;
	public SenderAlarmMessage() {
		flag = true;
	}

	public void sendMessage(String message) {
		if (flag) {
			System.out.println(message);
			flag = false;
		}
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}

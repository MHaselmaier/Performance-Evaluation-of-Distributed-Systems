package model.event;

import model.component.Manager;

public abstract class Event
{
	protected double actionTime;

	protected Event(double actionTime)
	{
		this.actionTime = actionTime;
	}

	public void action()
	{
		Manager.setTime(this.actionTime);
	}

	public double getActionTime()
	{
		return this.actionTime;
	}
}
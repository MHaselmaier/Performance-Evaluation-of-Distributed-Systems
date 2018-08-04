package model.event;

import model.component.Manager;

public class DurationEvent extends Event
{
	public DurationEvent(double actionTime)
	{
		super(actionTime);
	}

	@Override
	public void action()
	{
		super.action();
		
		// Free the station
		Manager.getService().free();
	}
}
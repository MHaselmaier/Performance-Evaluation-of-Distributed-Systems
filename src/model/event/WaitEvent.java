package model.event;

import model.component.Manager;
import model.statistics.Statistic;

public class WaitEvent extends Event
{
	// The time the WaitEvent entered the waiting queue
	// Used to calculate the time spend in the waiting queue
	private double arrivalTime;

	public WaitEvent()
	{
		// The actiontime will be right after the next DurationEvent
		super(Manager.getEventQueue().getNextDurationTime() + Double.MIN_VALUE);

		// Reserve a space in the waiting queue
		Manager.getService().addWaitEvent();
		
		// Update the overflow statistic
		Manager.updateOutputStatistic(Statistic.OVERFLOW, (Manager.getService().isOverflown() ? 1 : 0));
		
		this.arrivalTime = Manager.getTime();
	}

	@Override
	public void action()
	{
		super.action();
		
		// How to handle the event
		switch (Manager.getService().reserve())
		{
		case WAITING:
			// Still has to wait
			
			// The next actiontime will be right after the next DurationEvent
			this.actionTime = Manager.getEventQueue().getNextDurationTime() + Double.MIN_VALUE;
			
			// Reenter the EventQueue
			Manager.getEventQueue().insertEvent(this);
			break;
		default:
			// Does not have to wait any longer
			
			// Free the space in the waiting queue
			Manager.getService().removeWaitEvent();
			
			// The time the DurationEvent will spend in the station
			double duration = Manager.getInputStatistic(Statistic.DURATION);
			double time = Manager.getTime();
			
			// Update the time statistics
			Manager.updateOutputStatistic(Statistic.SYS_TIME, (duration + time) - this.arrivalTime);
			Manager.updateOutputStatistic(Statistic.DUR_TIME, duration);
			Manager.updateOutputStatistic(Statistic.QUEUE_TIME, time - this.arrivalTime);
			Manager.updateOutputStatistic(Statistic.QUEUE_ONLY_TIME, time - this.arrivalTime);

			// Insert a new DurationEvent in the EventQueue
			Manager.getEventQueue().insertEvent(new DurationEvent(duration + time));
			break;
		}
	}
}
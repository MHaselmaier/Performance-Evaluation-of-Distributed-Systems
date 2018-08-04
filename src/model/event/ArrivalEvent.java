package model.event;

import model.component.Manager;
import model.statistics.Statistic;

public class ArrivalEvent extends Event
{
	public ArrivalEvent(double actionTime)
	{
		super(actionTime);
	}

	@Override
	public void action()
	{
		super.action();

		// Update average amount of requests
		Manager.updateOutputStatistic(Statistic.SYS_AMOUNT, Manager.getEventQueue().sysAmount());
		Manager.updateOutputStatistic(Statistic.DUR_AMOUNT, Manager.getEventQueue().durAmount());
		Manager.updateOutputStatistic(Statistic.QUEUE_AMOUNT, Manager.getEventQueue().queueAmount());

		// How to handle the new event
		switch (Manager.getService().reserve())
		{
		case BLOCKED:
			// Update the block and wait statistics
			Manager.updateOutputStatistic(Statistic.BLOCK, 1.0);
			Manager.updateOutputStatistic(Statistic.WAIT, 0.0);
			break;
		case WAITING:
			// Update the block and wait statistics
			Manager.updateOutputStatistic(Statistic.BLOCK, 0.0);
			Manager.updateOutputStatistic(Statistic.WAIT, 1.0);

			// Insert a new WaitEvent in the EventQueue
			Manager.getEventQueue().insertEvent(new WaitEvent());
			break;
		case FREE:
			// Update the block and wait statistics
			Manager.updateOutputStatistic(Statistic.BLOCK, 0.0);
			Manager.updateOutputStatistic(Statistic.WAIT, 0.0);
			
			// The time the DurationEvent will spend in the station
			double duration = Manager.getInputStatistic(Statistic.DURATION);
			
			// Update the time statistics
			Manager.updateOutputStatistic(Statistic.SYS_TIME, duration);
			Manager.updateOutputStatistic(Statistic.DUR_TIME, duration);
			Manager.updateOutputStatistic(Statistic.QUEUE_TIME, 0.0);
			
			// Update the overflow statistic
			Manager.updateOutputStatistic(Statistic.OVERFLOW, 0);
			
			// Insert a new DurationEvent in the EventQueue
			Manager.getEventQueue().insertEvent(new DurationEvent(duration + Manager.getTime()));
			break;
		}

		// Next ArrivalEvent
		this.actionTime = Manager.getInputStatistic(Statistic.ARRIVAL) + Manager.getTime();
		Manager.getEventQueue().insertEvent(this);
	}
}
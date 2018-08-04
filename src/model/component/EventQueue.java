package model.component;

import java.util.List;
import java.util.LinkedList;

import model.event.Event;
import model.event.ArrivalEvent;
import model.event.WaitEvent;
import model.event.DurationEvent;

public class EventQueue
{
	private List<Event> queue = new LinkedList<Event>();
	private int sysAmount, durAmount, queueAmount;

	public double getNextDurationTime()
	{
		for (int i = this.queue.size() - 1; 0 <= i; --i)
		{
			Event event = this.queue.get(i);
			if (event instanceof DurationEvent)
			{
				return event.getActionTime();
			}
		}
		return 0.0;
	}

	public Event nextEvent()
	{
		Event e = this.queue.remove(this.queue.size() - 1);

		if (!(e instanceof ArrivalEvent))
		{
			--this.sysAmount;
		}
		if (e instanceof DurationEvent)
		{
			--this.durAmount;
		}
		else if (e instanceof WaitEvent)
		{
			--this.queueAmount;
		}

		return e;
	}

	public void insertEvent(Event e)
	{
		if (!(e instanceof ArrivalEvent))
		{
			++this.sysAmount;
		}
		if (e instanceof DurationEvent)
		{
			++this.durAmount;
		}
		else if (e instanceof WaitEvent)
		{
			++this.queueAmount;
		}

		for (int i = 0; this.queue.size() > i; ++i)
		{
			if (e.getActionTime() >= this.queue.get(i).getActionTime())
			{
				this.queue.add(i, e);
				return;
			}
		}
		this.queue.add(this.queue.size(), e);
	}

	public int sysAmount()
	{
		return this.sysAmount;
	}

	public int durAmount()
	{
		return this.durAmount;
	}

	public int queueAmount()
	{
		return this.queueAmount;
	}
}
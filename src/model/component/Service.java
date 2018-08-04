package model.component;

public class Service
{
	private int maxDur, currDur;
	private int maxQueue, currQueue;
	private boolean overflow;

	public Service(int maxDur, int maxQueue, boolean overflow)
	{
		this.maxDur = maxDur;
		this.maxQueue = maxQueue;
		this.overflow = overflow;
	}

	public void addWaitEvent()
	{
		++this.currQueue;
	}

	public void removeWaitEvent()
	{
		--this.currQueue;
	}

	public boolean isOverflown()
	{
		return (this.currQueue > this.maxQueue) && (-1 != this.maxQueue);
	}

	public ServiceState reserve()
	{
		if (this.currDur < this.maxDur)
		{
			++this.currDur;
			return ServiceState.FREE;
		}
		if ((-1 == this.maxQueue || this.overflow) || (this.currQueue < this.maxQueue))
		{
			return ServiceState.WAITING;
		}
		return ServiceState.BLOCKED;
	}

	public void free()
	{
		--this.currDur;
	}
	
	public static enum ServiceState
	{
		FREE, BLOCKED, WAITING
	}
}
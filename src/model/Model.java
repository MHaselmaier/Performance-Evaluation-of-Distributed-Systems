package model;

import model.component.Clock;
import model.component.EventQueue;
import model.component.Manager;
import model.component.Service;
import model.event.ArrivalEvent;
import model.statistics.InputStatistics;
import model.statistics.OutputStatistics;
import model.statistics.Statistic;
import view.View;

public class Model
{
	private View view;

	public void addView(View view)
	{
		this.view = view;
	}

	public void init(double rateA, double rateB, int n, int length, boolean overflow, int accuracy)
	{
		Manager.reset();
		Manager.setClock(new Clock());
		Manager.setEventQueue(new EventQueue());
		Manager.setService(new Service(n, length, overflow));
		Manager.addInputStatistic(Statistic.ARRIVAL, new InputStatistics(rateA));
		Manager.addInputStatistic(Statistic.DURATION, new InputStatistics(rateB));
		
		// Setup the OutputStatistics for the specified system
		if (0 == length && !overflow)
		{
			// Blocksystem
			Manager.addOutputStatistic(Statistic.BLOCK, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.SYS_AMOUNT, new OutputStatistics(accuracy));
		}
		if (-1 == length && !overflow)
		{
			// Waitsystem
			Manager.addOutputStatistic(Statistic.WAIT, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.SYS_AMOUNT, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.DUR_AMOUNT, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.QUEUE_AMOUNT, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.SYS_TIME, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.DUR_TIME, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.QUEUE_TIME, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.QUEUE_ONLY_TIME, new OutputStatistics(accuracy));
		}
		if (0 < length && overflow)
		{
			// Overflowsystem
			Manager.addOutputStatistic(Statistic.OVERFLOW, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.SYS_AMOUNT, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.DUR_AMOUNT, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.QUEUE_AMOUNT, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.SYS_TIME, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.DUR_TIME, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.QUEUE_TIME, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.QUEUE_ONLY_TIME, new OutputStatistics(accuracy));
		}
		if (0 < length && !overflow)
		{
			// Blockingwaitsystem
			Manager.addOutputStatistic(Statistic.BLOCK, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.SYS_AMOUNT, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.DUR_AMOUNT, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.QUEUE_AMOUNT, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.SYS_TIME, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.DUR_TIME, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.QUEUE_TIME, new OutputStatistics(accuracy));
			Manager.addOutputStatistic(Statistic.QUEUE_ONLY_TIME, new OutputStatistics(accuracy));
		}
	}

	public void run()
	{
		Manager.getEventQueue().insertEvent(new ArrivalEvent(Manager.getInputStatistic(Statistic.ARRIVAL)));

		do
		{
			for (int rounds = 0; 10_000_000 > rounds; ++rounds)
			{
				Manager.getEventQueue().nextEvent().action();
			}
		} while (!Manager.stop());

		this.view.update();
	}

	public static void main(String[] args)
	{
		Model model = new Model();
		View view = new View(model);
		model.addView(view);
	}
}
package model.component;

import java.util.HashMap;
import java.util.Map;

import model.statistics.InputStatistics;
import model.statistics.OutputStatistics;
import model.statistics.Statistic;

public class Manager
{	
	private static Map<Statistic, OutputStatistics> outStatistics;
	private static Map<Statistic, InputStatistics> inStatistics;
	private static Service service;
	private static EventQueue queue;
	private static Clock clock;

	public static void reset()
	{
		Manager.outStatistics = new HashMap<Statistic, OutputStatistics>();
		Manager.inStatistics = new HashMap<Statistic, InputStatistics>();
		Manager.service = null;
		Manager.queue = null;
		Manager.clock = null;
	}

	public static void addOutputStatistic(Statistic statistic, OutputStatistics outStatistic)
	{
		Manager.outStatistics.put(statistic, outStatistic);
	}

	public static double getOutputStatistic(Statistic statistic)
	{
		OutputStatistics outputStatistic = Manager.outStatistics.get(statistic);
		if (null != outputStatistic)
		{
			return outputStatistic.get();
		}
		else
		{
			return Double.NaN;
		}
	}

	public static void updateOutputStatistic(Statistic statistic, double sample)
	{
		OutputStatistics outputStatistic = Manager.outStatistics.get(statistic);
		if (null != outputStatistic)
		{
			outputStatistic.update(sample);
		}
	}

	public static boolean stop()
	{
		for (Map.Entry<Statistic, OutputStatistics> entry: Manager.outStatistics.entrySet())
		{
			if (!entry.getValue().stop())
			{
				return false;
			}
		}
		return true;
	}

	public static void addInputStatistic(Statistic statistic, InputStatistics inStatistic)
	{
		Manager.inStatistics.put(statistic, inStatistic);
	}

	public static double getInputStatistic(Statistic statistic)
	{
		InputStatistics inputStatistic = Manager.inStatistics.get(statistic);
		if (null != inputStatistic)
		{
			return inputStatistic.get();
		}
		else
		{
			return Double.NaN;
		}
	}

	public static void setService(Service service)
	{
		Manager.service = service;
	}

	public static Service getService()
	{
		return Manager.service;
	}

	public static void setEventQueue(EventQueue queue)
	{
		Manager.queue = queue;
	}

	public static EventQueue getEventQueue()
	{
		return Manager.queue;
	}

	public static void setClock(Clock clock)
	{
		Manager.clock = clock;
	}

	public static double getTime()
	{
		return Manager.clock.getTime();
	}

	public static void setTime(double time)
	{
		Manager.clock.setTime(time);
	}
}
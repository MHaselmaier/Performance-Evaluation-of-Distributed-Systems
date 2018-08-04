package model.statistics;

import model.util.Formula;

public class InputStatistics
{
	private double lambda;

	public InputStatistics(double lambda)
	{
		this.lambda = lambda;
	}

	public double get()
	{
		return Formula.reverseDistribution(Math.random(), this.lambda);
	}
}
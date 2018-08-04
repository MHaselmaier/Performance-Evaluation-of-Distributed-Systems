package model.statistics;

public class OutputStatistics
{
	private double sum, square;
	private double accuracy;
	private long n;

	public OutputStatistics(int accuracy)
	{
		this.accuracy = Math.pow(10, -accuracy * 2);
	}

	public void update(double sample)
	{
		++this.n;

		double factor = (this.n - 1);
		this.sum = (factor * this.sum + sample) / this.n;
		this.square = (factor * this.square + Math.pow(sample, 2)) / this.n;
	}

	public boolean stop()
	{
		if (0 == this.n) return true;
		
		double totalVariance = (this.square - Math.pow(this.sum, 2)) / this.n;
		return (totalVariance < this.accuracy);
	}

	public double get()
	{
		return this.sum;
	}
}
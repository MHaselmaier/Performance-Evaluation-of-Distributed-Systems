package model.util;

public class Formula
{
	public static double T(double lambda, double mue, int n)
	{
		return Tqueue(lambda, lambda / mue, n) + Tbe(mue);
	}

	public static double Tbe(double mue)
	{
		return 1.0 / mue;
	}

	public static double Tqueue(double lambda, double a, int n)
	{
		return Nqueue(a, n) / lambda;
	}

	public static double TqueueOnly(double lambda, double a, int n)
	{
		return (1.0 / lambda) * (a / (n - a));
	}

	public static double N(double a, int n)
	{
		return Nqueue(a, n) + Nbe(a);
	}

	public static double Nbe(double a)
	{
		return a;
	}

	public static double Nqueue(double a, int n)
	{
		return ErlangC(a, n) * (a / (n - a));
	}
	
	public static double ErlangB(double a, int n)
	{
		double sum = 0;
		for (int i = 0; n >= i; ++i)
		{
			sum += Math.pow(a, i) / fak(i);
		}

		return (Math.pow(a, n) / fak(n)) / sum;
	}

	public static double ErlangC(double a, int n)
	{
		double nominator = (Math.pow(a, n) / fak(n)) * (n / (n - a));

		double sum = 0;
		for (int i = 0; n - 1 >= i; ++i)
		{
			sum += Math.pow(a, i) / fak(i);
		}

		return (nominator / (sum + nominator));
	}

	public static double reverseDistribution(double y, double lambda)
	{
		return -(Math.log(1 - y) / lambda);
	}

	private static int fak(int n)
	{
		int fak = 1;
		for (int i = 1; n >= i; ++i)
		{
			fak *= i;
		}
		return fak;
	}
}
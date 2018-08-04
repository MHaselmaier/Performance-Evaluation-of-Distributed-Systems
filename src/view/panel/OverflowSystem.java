package view.panel;

import model.Model;
import model.component.Manager;
import model.statistics.Statistic;

public class OverflowSystem extends HybridSystem
{	
	public OverflowSystem(Model model)
	{
		super(model);

		super.start.addActionListener(ae ->
		{
			OverflowSystem.super.model.init((OverflowSystem.super.rateA = Double.parseDouble(OverflowSystem.super.tRateA.getText())),
				(OverflowSystem.super.rateB = Double.parseDouble(OverflowSystem.super.tRateB.getText())), 
				(OverflowSystem.super.n = Integer.parseInt(OverflowSystem.super.tN.getText())),
				Integer.parseInt(OverflowSystem.super.tLength.getText()),
				true, // enable overflow
				Integer.parseInt(OverflowSystem.super.tAccuracy.getText()));
			OverflowSystem.super.model.run();
		});
	}

	@Override
	public void update()
	{
		super.prob.setText("\u00DCberl\u00E4ngen-Wahrscheinlichkeit: " + Manager.getOutputStatistic(Statistic.OVERFLOW));
		super.sysAmount.setText("Mittlere Anzahl im System: " + Manager.getOutputStatistic(Statistic.SYS_AMOUNT));
		super.durAmount.setText("Mittlere Anzahl beim Bearbeiten: " + Manager.getOutputStatistic(Statistic.DUR_AMOUNT));
		super.queueAmount.setText("Mittlere Anzahl beim Warten: " + Manager.getOutputStatistic(Statistic.QUEUE_AMOUNT));
		super.sysTime.setText("Mittlere Zeit im System: " + Manager.getOutputStatistic(Statistic.SYS_TIME));
		super.durTime.setText("Mittlere Zeit beim Bearbeiten: " + Manager.getOutputStatistic(Statistic.DUR_TIME));
		super.queueTime.setText("Mittlere Zeit beim Warten: " + Manager.getOutputStatistic(Statistic.QUEUE_TIME));
		super.queueOnlyTime.setText("Mittlere Zeit beim Warten(nur Wartende): " + Manager.getOutputStatistic(Statistic.QUEUE_ONLY_TIME));
	}
}
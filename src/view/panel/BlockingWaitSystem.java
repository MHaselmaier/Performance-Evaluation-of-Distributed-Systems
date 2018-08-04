package view.panel;

import model.Model;
import model.component.Manager;
import model.statistics.Statistic;

public class BlockingWaitSystem extends HybridSystem
{
	public BlockingWaitSystem(Model model) {
		super(model);

		super.start.addActionListener(ae ->
		{
			BlockingWaitSystem.super.model.init((BlockingWaitSystem.super.rateA = Double.parseDouble(BlockingWaitSystem.this.tRateA.getText())),
				(BlockingWaitSystem.super.rateB = Double.parseDouble(BlockingWaitSystem.super.tRateB.getText())), 
				(BlockingWaitSystem.super.n = Integer.parseInt(BlockingWaitSystem.super.tN.getText())),
				Integer.parseInt(BlockingWaitSystem.super.tLength.getText()),
				false, // disable overflow
				Integer.parseInt(BlockingWaitSystem.super.tAccuracy.getText()));
			BlockingWaitSystem.super.model.run();
		});
	}

	public void update()
	{
		super.prob.setText("Blockier-Wahrscheinlichkeit: " + Manager.getOutputStatistic(Statistic.BLOCK));
		super.sysAmount.setText("Mittlere Anzahl im System: " + Manager.getOutputStatistic(Statistic.SYS_AMOUNT));
		super.durAmount.setText("Mittlere Anzahl beim Bearbeiten: " + Manager.getOutputStatistic(Statistic.DUR_AMOUNT));
		super.queueAmount.setText("Mittlere Anzahl beim Warten: " + Manager.getOutputStatistic(Statistic.QUEUE_AMOUNT));
		super.sysTime.setText("Mittlere Zeit im System: " + Manager.getOutputStatistic(Statistic.SYS_TIME));
		super.durTime.setText("Mittlere Zeit beim Bearbeiten: " + Manager.getOutputStatistic(Statistic.DUR_TIME));
		super.queueTime.setText("Mittlere Zeit beim Warten: " + Manager.getOutputStatistic(Statistic.QUEUE_TIME));
		super.queueOnlyTime.setText("Mittlere Zeit beim Warten(nur Wartende): " + Manager.getOutputStatistic(Statistic.QUEUE_ONLY_TIME));
	}
}
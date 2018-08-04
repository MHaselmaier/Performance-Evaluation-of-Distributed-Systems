package view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Model;
import model.component.Manager;
import model.statistics.Statistic;
import model.util.Formula;

public class WaitSystem extends SystemPanel
{	
	public WaitSystem(Model model)
	{
		super(model);
		
		super.start.addActionListener(ae ->
		{
			WaitSystem.super.model.init((WaitSystem.super.rateA = Double.parseDouble(WaitSystem.super.tRateA.getText())),
				(WaitSystem.super.rateB = Double.parseDouble(WaitSystem.super.tRateB.getText())), 
				(WaitSystem.super.n = Integer.parseInt(WaitSystem.super.tN.getText())),
				-1, // infinit waiting line
				false, // disable overflow
				Integer.parseInt(WaitSystem.super.tAccuracy.getText()));
			WaitSystem.super.model.run();
		});
		super.add(super.start, BorderLayout.SOUTH);
		
		initPanels();
	}

	@Override
	protected void initPanels()
	{
		JPanel westPanel = new JPanel(new GridLayout(7, 2));
		westPanel.add(super.lRateA);
		westPanel.add(super.tRateA);
		westPanel.add(super.lRateB);
		westPanel.add(super.tRateB);
		westPanel.add(super.lN);
		westPanel.add(super.tN);
		westPanel.add(super.lAccuracy);
		westPanel.add(super.tAccuracy);
		super.add(westPanel, BorderLayout.WEST);
		
		JPanel eastPanel = new JPanel(new GridLayout(0, 1));
		eastPanel.add(super.prob);
		eastPanel.add(super.probFormula);
		eastPanel.add(super.sysAmount);
		eastPanel.add(super.sysAmountFormula);
		eastPanel.add(super.durAmount);
		eastPanel.add(super.durAmountFormula);
		eastPanel.add(super.queueAmount);
		eastPanel.add(super.queueAmountFormula);
		eastPanel.add(super.sysTime);
		eastPanel.add(super.sysTimeFormula);
		eastPanel.add(super.durTime);
		eastPanel.add(super.durTimeFormula);
		eastPanel.add(super.queueTime);
		eastPanel.add(super.queueTimeFormula);
		eastPanel.add(super.queueOnlyTime);
		eastPanel.add(super.queueOnlyTimeFormula);
		super.add(eastPanel, BorderLayout.EAST);
	}
	
	@Override
	public void update()
	{
		super.prob.setText("Warte-Wahrscheinlichkeit: " + Manager.getOutputStatistic(Statistic.WAIT));
		super.probFormula.setText("ErlangC-Formel-Wert: " + Formula.ErlangC(super.rateA / super.rateB, super.n));

		super.sysAmount.setText("Mittlere Anzahl im System: " + Manager.getOutputStatistic(Statistic.SYS_AMOUNT));
		super.sysAmountFormula.setText("Formel-Wert: " + Formula.N(super.rateA / super.rateB, super.n));

		this.durAmount.setText("Mittlere Anzahl beim Bearbeiten: " + Manager.getOutputStatistic(Statistic.DUR_AMOUNT));
		this.durAmountFormula.setText("Formel-Wert: " + Formula.Nbe(super.rateA / super.rateB));

		super.queueAmount.setText("Mittlere Anzahl beim Warten: " + Manager.getOutputStatistic(Statistic.QUEUE_AMOUNT));
		super.queueAmountFormula.setText("Formel-Wert: " + Formula.Nqueue(super.rateA / super.rateB, super.n));

		super.sysTime.setText("Mittlere Zeit im System: " + Manager.getOutputStatistic(Statistic.SYS_TIME));
		super.sysTimeFormula.setText("Formel-Wert: " + Formula.T(super.rateA, super.rateB, super.n));

		super.durTime.setText("Mittlere Zeit beim Bearbeiten: " + Manager.getOutputStatistic(Statistic.DUR_TIME));
		super.durTimeFormula.setText("Formel-Wert: " + Formula.Tbe(super.rateB));

		super.queueTime.setText("Mittlere Zeit beim Warten: " + Manager.getOutputStatistic(Statistic.QUEUE_TIME));
		super.queueTimeFormula.setText("Formel-Wert: " + Formula.Tqueue(super.rateA, super.rateA / super.rateB, super.n));

		super.queueOnlyTime.setText("Mittlere Zeit beim Warten(nur Wartende): " + Manager.getOutputStatistic(Statistic.QUEUE_ONLY_TIME));
		super.queueOnlyTimeFormula.setText("Formel-Wert: " + Formula.TqueueOnly(super.rateA, super.rateA / super.rateB, super.n));
	}
}
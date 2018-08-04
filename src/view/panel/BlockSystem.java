package view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Model;
import model.component.Manager;
import model.statistics.Statistic;
import model.util.Formula;

public class BlockSystem extends SystemPanel
{
	public BlockSystem(Model model)
	{
		super(model);
		
		super.start.addActionListener(ae ->
		{
			BlockSystem.super.model.init((BlockSystem.super.rateA = Double.parseDouble(BlockSystem.super.tRateA.getText())),
				(BlockSystem.super.rateB = Double.parseDouble(BlockSystem.super.tRateB.getText())), 
				(BlockSystem.super.n = Integer.parseInt(BlockSystem.super.tN.getText())),
				0, // no waiting line
				false, // disable overflow
				Integer.parseInt(BlockSystem.super.tAccuracy.getText()));
			BlockSystem.super.model.run();
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
		
		JPanel eastPanel = new JPanel(new GridLayout(7, 1));
		eastPanel.add(super.prob);
		eastPanel.add(super.probFormula);
		eastPanel.add(super.sysAmount);
		eastPanel.add(super.sysAmountFormula);
		super.add(eastPanel, BorderLayout.EAST);
	}
	
	@Override
	public void update()
	{
		super.prob.setText("Blockier-Wahrscheinlichkeit: " + Manager.getOutputStatistic(Statistic.BLOCK));
		super.probFormula.setText("ErlangB-Formel-Wert: " + Formula.ErlangB(super.rateA / super.rateB, super.n));

		super.sysAmount.setText("Mittlere Anzahl im System: " + Manager.getOutputStatistic(Statistic.SYS_AMOUNT));
		super.sysAmountFormula.setText("Formel-Wert: " + Formula.Nbe(super.rateA / super.rateB) * (1 - Formula.ErlangB(super.rateA / super.rateB, super.n)));
	}
}
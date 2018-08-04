package view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Model;

public abstract class HybridSystem extends SystemPanel
{	
	protected JLabel lLength = new JLabel("L\u00E4nge der Warteschlange");
	
	protected JTextField tLength = new JTextField("3");

	public HybridSystem(Model model)
	{
		super(model);
		super.add(super.start, BorderLayout.SOUTH);
		
		this.lLength.setFont(super.font);

		initPanels();
	}
	
	@Override
	protected void initPanels()
	{
		JPanel westPanel = new JPanel(new GridLayout(5, 2));
		westPanel.add(super.lRateA);
		westPanel.add(super.tRateA);
		westPanel.add(super.lRateB);
		westPanel.add(super.tRateB);
		westPanel.add(super.lN);
		westPanel.add(super.tN);
		westPanel.add(this.lLength);
		westPanel.add(this.tLength);
		westPanel.add(super.lAccuracy);
		westPanel.add(super.tAccuracy);
		super.add(westPanel, BorderLayout.WEST);

		JPanel eastPanel = new JPanel(new GridLayout(8, 1));
		eastPanel.add(super.prob);
		eastPanel.add(super.sysAmount);
		eastPanel.add(super.durAmount);
		eastPanel.add(super.queueAmount);
		eastPanel.add(super.sysTime);
		eastPanel.add(super.durTime);
		eastPanel.add(super.queueTime);
		eastPanel.add(super.queueOnlyTime);
		super.add(eastPanel, BorderLayout.EAST);
	}	
}
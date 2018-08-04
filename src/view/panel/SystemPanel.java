package view.panel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Model;

public abstract class SystemPanel extends JPanel
{	
	// The model and its parameters
	protected Model model;

	protected double rateA, rateB;
	protected int n;

	// The input TextField Labels
	protected JLabel lRateA = new JLabel("Ankunftsrate");
	protected JLabel lRateB = new JLabel("Bedienrate");
	protected JLabel lN = new JLabel("Anzahl Bedieneinheiten");
	protected JLabel lAccuracy = new JLabel("Abbruch-Genauigkeit");

	// The input TextFields
	protected JTextField tRateA = new JTextField("80");
	protected JTextField tRateB = new JTextField("120");
	protected JTextField tN = new JTextField("1");
	protected JTextField tAccuracy = new JTextField("3");

	// The output Labels
	protected JLabel prob = new JLabel();
	protected JLabel probFormula = new JLabel();
	protected JLabel sysAmount = new JLabel();
	protected JLabel sysAmountFormula = new JLabel();
	protected JLabel durAmount = new JLabel();
	protected JLabel durAmountFormula = new JLabel();
	protected JLabel queueAmount = new JLabel();
	protected JLabel queueAmountFormula = new JLabel();
	protected JLabel sysTime = new JLabel();
	protected JLabel sysTimeFormula = new JLabel();
	protected JLabel durTime = new JLabel();
	protected JLabel durTimeFormula = new JLabel();
	protected JLabel queueTime = new JLabel();
	protected JLabel queueTimeFormula = new JLabel();
	protected JLabel queueOnlyTime = new JLabel();
	protected JLabel queueOnlyTimeFormula = new JLabel();
	
	// The button to start the simulation
	protected JButton start = new JButton("Starten");

	protected Font font = new Font("Helvetica", Font.BOLD, 18);

	public SystemPanel(Model model)
	{
		super(new BorderLayout()); 
		this.model = model;
		
		this.lRateA.setFont(this.font);
		this.lRateB.setFont(this.font);
		this.lN.setFont(this.font);
		this.lAccuracy.setFont(this.font);
		
		this.prob.setFont(this.font);
		this.prob.setHorizontalAlignment(SwingConstants.RIGHT);

		initLabels();
	}
	
	private void initLabels()
	{
		this.prob.setFont(this.font);
		this.prob.setHorizontalAlignment(SwingConstants.RIGHT);
		this.probFormula.setFont(this.font);
		this.probFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		this.sysAmount.setFont(this.font);
		this.sysAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		this.sysAmountFormula.setFont(this.font);
		this.sysAmountFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		this.durAmount.setFont(this.font);
		this.durAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		this.durAmountFormula.setFont(this.font);
		this.durAmountFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		this.queueAmount.setFont(this.font);
		this.queueAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		this.queueAmountFormula.setFont(this.font);
		this.queueAmountFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		this.sysTime.setFont(this.font);
		this.sysTime.setHorizontalAlignment(SwingConstants.RIGHT);
		this.sysTimeFormula.setFont(this.font);
		this.sysTimeFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		this.durTime.setFont(this.font);
		this.durTime.setHorizontalAlignment(SwingConstants.RIGHT);
		this.durTimeFormula.setFont(this.font);
		this.durTimeFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		this.queueTime.setFont(this.font);
		this.queueTime.setHorizontalAlignment(SwingConstants.RIGHT);
		this.queueTimeFormula.setFont(this.font);
		this.queueTimeFormula.setHorizontalAlignment(SwingConstants.RIGHT);
		this.queueOnlyTime.setFont(this.font);
		this.queueOnlyTime.setHorizontalAlignment(SwingConstants.RIGHT);
		this.queueOnlyTimeFormula.setFont(this.font);
		this.queueOnlyTimeFormula.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	protected abstract void initPanels();
	
	public abstract void update();
}
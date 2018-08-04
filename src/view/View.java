package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import model.Model;
import view.panel.BlockSystem;
import view.panel.BlockingWaitSystem;
import view.panel.OverflowSystem;
import view.panel.WaitSystem;

public class View extends JFrame
{
	private JTabbedPane tabbedPane = new JTabbedPane();

	private BlockSystem block;
	private WaitSystem wait;
	private OverflowSystem overflow;
	private BlockingWaitSystem blockingWait;

	public View(Model model)
	{
		super("M/M/n - Systeme");

		// BlockSystem
		this.block = new BlockSystem(model);
		this.tabbedPane.addTab("Verlust-System", null, this.block, "Verlust-System");

		// WaitSystem
		this.wait = new WaitSystem(model);
		this.tabbedPane.addTab("Warte-System", null, this.wait, "Warte-System");

		// OverflowSystem
		this.overflow = new OverflowSystem(model);
		this.tabbedPane.addTab("Hybrid-System (\u00DCberlauf)", null, this.overflow, "Hybrid-System (\u00DCberlauf)");

		// BlockingWaitSystem
		this.blockingWait = new BlockingWaitSystem(model);
		this.tabbedPane.addTab("Hybrid-System (Verlust)", null, this.blockingWait, "Hybrid-System (Verlust)");

		super.add(this.tabbedPane);

		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setSize(1100, 720);
		super.setVisible(true);
	}

	public void update()
	{
		switch(this.tabbedPane.getSelectedIndex())
		{
		case 0:
			this.block.update();
			break;
		case 1:
			this.wait.update();
			break;
		case 2:
			this.overflow.update();
			break;
		case 3:
			this.blockingWait.update();
			break;
		}
	}
}
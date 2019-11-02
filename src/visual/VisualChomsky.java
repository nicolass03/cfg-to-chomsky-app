package visual;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class VisualChomsky extends JFrame{
	
	private PanelGIC gicPane;
	private PanelOptions optPane;
	private PanelResult resPane;
	
	public VisualChomsky() {
		
		setLayout(new BorderLayout());
		setTitle("GIC to Chomsky");
		setPreferredSize(new Dimension(700,300));
		setResizable(false);
		setBackground(Color.WHITE);
		
		gicPane = new PanelGIC();
		optPane = new PanelOptions(this);
		resPane = new PanelResult();
		
		add(gicPane, BorderLayout.WEST);
		add(resPane, BorderLayout.CENTER);
		add(optPane, BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		VisualChomsky v = new VisualChomsky();
		v.setVisible(true);
	}

	public void resetGIC() {
		gicPane.resetTable();
	}

	public void convertGIC() {
		// TODO Auto-generated method stub
		
	}

}
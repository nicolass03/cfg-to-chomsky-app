package visual;

import java.awt.*;
import javax.swing.*;

import model.Chomsky;

@SuppressWarnings("serial")
public class VisualChomsky extends JFrame{
	
	private PanelGIC gicPane;
	private PanelOptions optPane;
	private PanelResult resPane;
	
	private Chomsky chomsky;
	
	public VisualChomsky() {
		
		setLayout(new BorderLayout());
		setTitle("GIC to Chomsky");
		setPreferredSize(new Dimension(900,300));
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
		chomsky = new Chomsky(gicPane.getValues());
		resPane.setValues(chomsky.getGrammar());
	}

}

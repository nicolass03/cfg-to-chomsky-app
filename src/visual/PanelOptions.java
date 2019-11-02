package visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class PanelOptions extends JPanel implements ActionListener{

	public static final String RESET = "R";
	public static final String CONVERT = "C";
	
	private JButton reset;
	private JButton convert;
	
	private VisualChomsky v;
	
	public PanelOptions(VisualChomsky visualChomsky) {
		setLayout(new FlowLayout());
		v = visualChomsky;
		setBorder(BorderFactory.createTitledBorder("Options"));
		setBackground(Color.WHITE);
		
		reset = new JButton("Reset");
		reset.setActionCommand(RESET);
		reset.addActionListener(this);
		reset.setBackground(Color.WHITE);
		
		convert = new JButton("Convert");
		convert.setActionCommand(CONVERT);
		convert.addActionListener(this);
		convert.setBackground(Color.WHITE);
		
		add(convert);
		add(reset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(RESET)) {
			v.resetGIC();
		}
		else if(e.getActionCommand().equals(CONVERT)) {
			v.convertGIC();
		}
	}

}

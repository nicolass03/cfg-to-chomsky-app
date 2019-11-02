package visual;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PanelGIC extends JPanel {

	private JTable table;
	private JScrollPane scroll;
	
	public PanelGIC() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("GIC"));
		setBackground(Color.WHITE);
		init();
	}
	
	public void init() {
		
		String[] col = {"Variables","Productions"};
		DefaultTableModel model = new DefaultTableModel(col,10);
		table = new JTable(model);
		table.setShowGrid(true);
		scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
		repaint();
		validate();
	}
	
	public String[][] getValues(){
		String[][] values = new String[table.getRowCount()][table.getColumnCount()];
		boolean done = false;
		for(int i = 0; i < table.getRowCount(); i++) {
			for(int j = 0; j < table.getColumnCount(); j++) {
				if(table.getValueAt(i, j) == null || table.getValueAt(i, j).equals("")) {
					done = true;
					break;					
				}
				else {
					values[i][j] = (String) table.getValueAt(i, j);
				}
			}
			if(done)
				break;
		}
		return values;
	}

	public void resetTable() {
		remove(scroll);
		init();
	}
}

package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PanelResult extends JPanel {

	private JTable table;
	private JScrollPane scroll;
	
	public PanelResult() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Resultant GIC in CNF"));
		setBackground(Color.WHITE);
		init();
	}
	
	public void init() {
		
		String[] col = {"Variables","Productions"};
		DefaultTableModel model = new DefaultTableModel(col,10);
		table = new JTable(model);
		table.setEnabled(false);
		table.setShowGrid(true);
		scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
		repaint();
		validate();
	}
	
	public void setValues(Map<Character, ArrayList<String>> map){
		int i = 0;
		try {
			for(Entry e : map.entrySet()) {
				Character v = (Character) e.getKey();
				ArrayList<String> p = (ArrayList<String>) e.getValue();
				String prods = p.toString();
				table.setValueAt(v+"", i, 0);
				table.setValueAt(prods, i, 1);
				i++;
			}			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Por favor presione Enter al terminar de introducir la gramática.");
		}
	}

	public void resetTable() {
		remove(scroll);
		init();
	}
}

package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

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
	
	public Map getValues(){
		Map<Character, ArrayList> values = Collections.synchronizedMap(
				  new LinkedHashMap<Character, ArrayList>());		
		for(int i = 0; i < table.getRowCount(); i++) {
			String a = ((String) table.getValueAt(i, 0));
			if(a != null && !a.equals("")) {
				Character v = a.charAt(0);
				String p = (String) table.getValueAt(i, 1);
				String[] prods = p.split(",");
				ArrayList productions = new ArrayList(Arrays.asList(prods));
				values.put(v, productions);
			}
			else {
				break;
			}
		}
		return values;
	}

	public void resetTable() {
		remove(scroll);
		init();
	}
}

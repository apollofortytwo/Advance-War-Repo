import javax.swing.JPanel;

import layout.TableLayout;

public class TablePanel extends JPanel {

	public TablePanel() {
		this.setSize(32 * Application.WIDTH, 32 * Application.HEIGHT);
		this.setLayout(new TableLayout(Application.size));
		this.setOpaque(false);
	}

	public void add(java.awt.Component comp, int x, int y) {
		this.add(comp, Integer.toString(x) + ","+ Integer.toString(y));
	}
}

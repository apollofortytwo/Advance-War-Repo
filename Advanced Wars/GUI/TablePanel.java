import javax.swing.JPanel;

import layout.TableLayout;

/**
 * JPanel that contains a grid grid is based on the size set by the Application
 * table
 * 
 * Components can be added to individual grids
 * 
 * @author Bryan
 *
 */
public class TablePanel extends JPanel {

	public static double[][] size;
	
	public static int WIDTH, HEIGHT;
	
	public static void setTable(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
	}
	
	
	
	public TablePanel() {
		this.setSize(32 * HEIGHT, 32 * WIDTH);
		this.setLayout(new TableLayout(TablePanel.size));
		this.setOpaque(false);
	}

	/**
	 * Adds a Component to a cell on the grid
	 * 
	 * @param comp
	 *            (Component)
	 * @param x
	 *            (X position on the grid)
	 * @param y
	 *            (Y position on the grid)
	 */
	public void add(java.awt.Component comp, int x, int y) {
		this.add(comp, Integer.toString(x) + "," + Integer.toString(y));
	}
}

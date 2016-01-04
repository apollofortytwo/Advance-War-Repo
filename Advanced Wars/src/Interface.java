import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import layout.TableLayout;

/**
 * acts as a display port for the classes Holds the mainFrame (Frame that holds
 * the game) holds the game table for the game manages the changes between the
 * information being present on the sides
 * 
 * @author ApolloFortyTwo
 *
 */
public class Interface {

	public static JFrame mainFrame;

	public static TablePanel tilePanel;
	public static TablePanel unitPanel;
	public static TablePanel attackingSpritePanel;

	private static JPanel blueTeamInfoContainer = new JPanel();
	private static JPanel redTeamInfoContainer = new JPanel();

	public static TeamStatPanel blueTeamInfo;
	public static TeamStatPanel redTeamInfo;

	public static void addBuildingInfo(Building building) {
		System.out.println(building.getTeam());
		if (building.getTeam().equals("Blue")) {
			if (Unit.selectedUnit == null) {
				blueTeamInfoContainer.removeAll();
				blueTeamInfoContainer.add(building.buildingPanel, BorderLayout.CENTER);
			} else if (Unit.selectedUnit != null && Unit.selectedUnit.getTeam() != building.getTeam()) {
				blueTeamInfoContainer.removeAll();
				blueTeamInfoContainer.add(building.buildingPanel, BorderLayout.CENTER);
			}
		} else if (building.getTeam().equals("Red")) {
			if (Unit.selectedUnit == null) {
				redTeamInfoContainer.removeAll();
				redTeamInfoContainer.add(building.buildingPanel, BorderLayout.CENTER);
			} else if (Unit.selectedUnit != null && Unit.selectedUnit.getTeam() != building.getTeam()) {
				redTeamInfoContainer.removeAll();
				redTeamInfoContainer.add(building.buildingPanel, BorderLayout.CENTER);
			}
		}

		blueTeamInfoContainer.revalidate();
		blueTeamInfoContainer.repaint();
		redTeamInfoContainer.revalidate();
		redTeamInfoContainer.repaint();

	}

	public static void addUnitInfo(Unit unit) {

		if (unit.getTeam().equals("Blue")) {
			if (Building.selectedBuilding == null) {
				blueTeamInfoContainer.removeAll();
				blueTeamInfoContainer.add(unit.infoPanel, BorderLayout.CENTER);
			} else if (Building.selectedBuilding != null
					&& !Building.selectedBuilding.getTeam().equals(unit.getTeam())) {
				blueTeamInfoContainer.removeAll();
				blueTeamInfoContainer.add(unit.infoPanel, BorderLayout.CENTER);
			}
		} else if (unit.getTeam().equals("Red")) {
			if (Building.selectedBuilding == null) {
				redTeamInfoContainer.removeAll();
				redTeamInfoContainer.add(unit.infoPanel, BorderLayout.CENTER);
			} else if (Building.selectedBuilding != null
					&& !Building.selectedBuilding.getTeam().equals(unit.getTeam())) {
				redTeamInfoContainer.removeAll();
				redTeamInfoContainer.add(unit.infoPanel, BorderLayout.CENTER);
			}
		}
		blueTeamInfoContainer.revalidate();
		blueTeamInfoContainer.repaint();
		redTeamInfoContainer.revalidate();
		redTeamInfoContainer.repaint();

	}

	public static void frame() {
		class ResizeListener implements ComponentListener {

			public void componentHidden(ComponentEvent e) {
			}

			public void componentMoved(ComponentEvent e) {
			}

			public void componentShown(ComponentEvent e) {
			}

			public void componentResized(ComponentEvent e) {
				Dimension newSize = e.getComponent().getBounds().getSize();
				System.out.println(newSize.getWidth() + ", " + newSize.getHeight());

				double n = ((newSize.getWidth() - 440) / 20);

				System.out.println(n);

				for (int row = 0; row < 20; row++) {
					for (int col = 0; col < 20; col++) {
						Terrain.terrainArray[row][col].resizeSprites((int) n, (int) n);

					}

				}

				Application.size = new double[][] {
						{ 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 }, // Columns
						{ 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 } // Rows;
				};
				;
				Application.setTable();

				/*
				 * Application.size = new double[][] { { n, n, n, n, n, n, n, n,
				 * n, n, n, n, n, n, n, n, n, n, n, n }, // Columns { n, n, n,
				 * n, n, n, n, n, n, n, n, n, n, n, n, n, n, n, n, n } // Rows;
				 * }; ;
				 */

				tilePanel.setSize((int) n * Application.WIDTH, (int) n * Application.HEIGHT);
				unitPanel.setSize((int) n * Application.WIDTH, (int) n * Application.HEIGHT);
				attackingSpritePanel.setSize((int) n * Application.WIDTH, (int) n * Application.HEIGHT);

				tilePanel.setLayout(new TableLayout(Application.size));
				unitPanel.setLayout(new TableLayout(Application.size));

				attackingSpritePanel.setLayout(new TableLayout(Application.size));

				tilePanel.revalidate();
				tilePanel.repaint();

				unitPanel.revalidate();
				unitPanel.repaint();
				layer.revalidate();
				layer.repaint();

			}

		}

		mainFrame = new JFrame("Advanced wars");
		mainFrame.addComponentListener(new ResizeListener());

		mainFrame.setSize(1080, 720);
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		mainFrame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {

					int save = JOptionPane.showConfirmDialog(mainFrame, "Would you like to save", "SAVE",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (save == JOptionPane.YES_OPTION) {
						mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						JFileChooser fc = new JFileChooser();
						fc.showSaveDialog(mainFrame);
						SaveGame.fileName = fc.getSelectedFile();
						new SaveGame();
						mainFrame.dispose();
					} else if (save == JOptionPane.NO_OPTION) {
						mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						mainFrame.dispose();
					} else if (save == JOptionPane.CANCEL_OPTION) {

					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setVisible(true);

	}

	static JLayeredPane layer;

	public static void initalize() {
		blueTeamInfo = new TeamStatPanel("Blue");

		blueTeamInfoContainer.setSize(blueTeamInfoContainer.getPreferredSize());
		blueTeamInfoContainer.add(blueTeamInfo, BorderLayout.CENTER);

		redTeamInfo = new TeamStatPanel("Red");
		redTeamInfoContainer.add(redTeamInfo, BorderLayout.CENTER);

		mainFrame.add(blueTeamInfoContainer, BorderLayout.WEST);
		mainFrame.add(redTeamInfoContainer, BorderLayout.EAST);

		JPanel turnPanels = new JPanel();
		turnPanels.add(TurnPanel.turnsElapsedLabel);
		turnPanels.add(TurnPanel.turnLabel);
		turnPanels.add(TurnPanel.timeCounter);

		layer = new JLayeredPane();

		tilePanel = new TablePanel();
		unitPanel = new TablePanel();
		attackingSpritePanel = new TablePanel();

		layer.add(Interface.tilePanel, new Integer(25));
		layer.add(Interface.unitPanel, new Integer(50));
		layer.add(Interface.attackingSpritePanel, new Integer(100));

		Interface.mainFrame.add(layer, BorderLayout.CENTER);
		Interface.mainFrame.add(turnPanels, BorderLayout.SOUTH);

	}

	public static void remove(String team) {
		if (Unit.selectedUnit != null) {
			if (Unit.selectedUnit.getTeam().equals(team)) {
				return;
			}
		}
		if (Building.selectedBuilding != null) {
			if (Building.selectedBuilding.getTeam().equals(team)) {
				return;
			}
		}
		if (team.equals("Blue")) {
			blueTeamInfoContainer.removeAll();
			blueTeamInfoContainer.add(blueTeamInfo, BorderLayout.CENTER);
		} else if (team.equals("Red")) {
			redTeamInfoContainer.removeAll();
			redTeamInfoContainer.add(redTeamInfo, BorderLayout.CENTER);
		}

		blueTeamInfoContainer.revalidate();
		blueTeamInfoContainer.repaint();
		redTeamInfoContainer.revalidate();
		redTeamInfoContainer.repaint();
	}

	public static void restore() {

		Unit.UnSelectedUnit();

		Building.selectedBuilding = null;

		blueTeamInfoContainer.removeAll();
		blueTeamInfoContainer.add(blueTeamInfo, BorderLayout.CENTER);
		redTeamInfoContainer.removeAll();
		redTeamInfoContainer.add(redTeamInfo, BorderLayout.CENTER);

		blueTeamInfoContainer.revalidate();
		blueTeamInfoContainer.repaint();
		redTeamInfoContainer.revalidate();
		redTeamInfoContainer.repaint();
	}

}

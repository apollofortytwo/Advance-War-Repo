import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * acts as a display port for the classes
 * Holds the mainFrame (Frame that holds the game)
 * holds the game table for the game
 * manages the changes between the information being present on the sides
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

		if (unit.getTeam().equals("Blue") ) {
			if (Building.selectedBuilding == null) {
				blueTeamInfoContainer.removeAll();
				blueTeamInfoContainer.add(unit.unitPanel, BorderLayout.CENTER);
			} else if (Building.selectedBuilding != null && !Building.selectedBuilding.getTeam().equals(unit.getTeam())) {
				blueTeamInfoContainer.removeAll();
				blueTeamInfoContainer.add(unit.unitPanel, BorderLayout.CENTER);
			}
		} else if (unit.getTeam().equals("Red")) {
			if (Building.selectedBuilding == null) {
				redTeamInfoContainer.removeAll();
				redTeamInfoContainer.add(unit.unitPanel, BorderLayout.CENTER);
			} else if (Building.selectedBuilding != null && !Building.selectedBuilding.getTeam().equals(unit.getTeam())) {
				redTeamInfoContainer.removeAll();
				redTeamInfoContainer.add(unit.unitPanel, BorderLayout.CENTER);
			}
		}
		blueTeamInfoContainer.revalidate();
		blueTeamInfoContainer.repaint();
		redTeamInfoContainer.revalidate();
		redTeamInfoContainer.repaint();

	}

	public static void frame() {
		mainFrame = new JFrame("Advanced wars");
		mainFrame.setSize(1080, 720);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					new SaveGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setVisible(true);

	}

	public static void initalize() {
		blueTeamInfo = new TeamStatPanel("Blue");

		blueTeamInfoContainer.setSize(blueTeamInfoContainer.getPreferredSize());
		blueTeamInfoContainer.add(blueTeamInfo, BorderLayout.CENTER);

		redTeamInfo = new TeamStatPanel("Red");
		redTeamInfoContainer.add(redTeamInfo, BorderLayout.CENTER);

		mainFrame.add(blueTeamInfoContainer, BorderLayout.WEST);
		mainFrame.add(redTeamInfoContainer, BorderLayout.EAST);

		JPanel turnPanels = new JPanel();
		turnPanels.add(TurnPanelLabel.turnsElapsedLabel);
		turnPanels.add(TurnPanelLabel.turnLabel);
		turnPanels.add(TurnPanelLabel.timeCounter);

		JLayeredPane layer = new JLayeredPane();
		
		tilePanel = new TablePanel();
		unitPanel = new TablePanel();
		attackingSpritePanel = new TablePanel();

		layer.add(Interface.tilePanel, new Integer(1));
		layer.add(Interface.unitPanel, new Integer(10));
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

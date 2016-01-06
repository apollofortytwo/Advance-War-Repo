import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import layout.TableLayout;

/**
 * (Created with every building) Lays out a set of information that describes
 * the coupled building stats Displayed on the (Team)InformationContainer
 * 
 * @author ApolloFortyTwo
 *
 */
public class BuildingInfoPanel extends JPanel implements ActionListener {

	private String team;
	private Building building;

	private JButton TANK, INFANTRY, ARTILIARY, HELICOPTER;
	private JLabel turnsTillExport, currentlyProducing;
	private String currentlyProducingString;
	double f = TableLayout.FILL;
	int x, y;

	double size[][] = { { f }, { 32, 32, 32, f, f, f, f, f, f } };
	private int hitPoints;
	private JLabel health;

	/**
	 * 
	 * @param building
	 *            (The building whose information will be described)
	 */
	public BuildingInfoPanel(Building building) {
		this.building = building;

		this.setTeam(building.team);
		this.setPreferredSize(new Dimension(200, 644));
		this.setLayout(new TableLayout(size));
		this.setBorder(BorderFactory.createEtchedBorder());
		buttons();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/**
		 * stops the building from reset the same production for the same unit
		 */
		if (!building.isDead() && building.getCurrentlyProducing() == null) {
			System.out.println(e.getActionCommand() + " IN PRODUCTION");

			building.setCurrentlyProducing(e.getActionCommand());

			building.setEndTimeOfProduction(UnitInfo.getProductionTime(e
					.getActionCommand()));
			this.updateInfo();

		} else if (!building.isDead()
				&& building.getCurrentlyProducing() != null
				&& !building.getCurrentlyProducing().equals(
						e.getActionCommand())) {
			System.out.println(e.getActionCommand() + " IN PRODUCTION");

			building.setCurrentlyProducing(e.getActionCommand());

			building.setEndTimeOfProduction(UnitInfo.getProductionTime(e
					.getActionCommand()));
			this.updateInfo();
		}

	}

	public void buttons() {
		turnsTillExport = new JLabel("TURNS: ");
		turnsTillExport.setHorizontalAlignment(0);

		currentlyProducing = new JLabel("Producing: ");
		currentlyProducing.setHorizontalAlignment(0);

		health = new JLabel("Building health: ");
		health.setHorizontalAlignment(0);
		health.setOpaque(true);

		TANK = new JButton("TANK: 10 turns");
		TANK.addActionListener(this);
		TANK.setActionCommand("TANK");

		INFANTRY = new JButton("INFANTRY: 5 turns");
		INFANTRY.addActionListener(this);
		INFANTRY.setActionCommand("INFANTRY");

		ARTILIARY = new JButton("ARTILLERY: 15 turns");
		ARTILIARY.addActionListener(this);
		ARTILIARY.setActionCommand("ARTILLERY");

		HELICOPTER = new JButton("HELICOPTER: ");
		HELICOPTER.addActionListener(this);
		HELICOPTER.setActionCommand("HELICOPTER");

		this.add(health, "0,0");
		this.add(turnsTillExport, "0,1");
		this.add(currentlyProducing, "0,2");
		this.add(TANK, "0,3");
		this.add(ARTILIARY, "0,4");
		this.add(INFANTRY, "0,5");
		this.add(HELICOPTER, "0,6");
		// this.add(ENGINNER, "0,7");
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * Called when something about the Buildings information is changed and is
	 * need to be re-displayed
	 */
	public void updateInfo() {
		if (building.getCurrentlyProducing() == null) {
			turnsTillExport.setText("NOT PRODUCING");
			currentlyProducing.setText("No units selected");
		} else {
			turnsTillExport.setText("Finished on turn: "
					+ Integer.toString(building.getEndTimeOfProduction()));
			currentlyProducingString = building.getCurrentlyProducing();
			currentlyProducing
					.setText("Producing: " + currentlyProducingString);
		}
		hitPoints = building.getHealth();
		health.setBorder(BorderFactory.createEtchedBorder(1));

		if (hitPoints >= 33) {
			health.setBackground(Color.green);
		} else if (hitPoints >= 16 && hitPoints < 33) {
			health.setBackground(Color.yellow);
		} else if (hitPoints >= 1 && hitPoints < 16) {
			health.setBackground(Color.red);
		}

		health.setText("Building health: " + hitPoints);
		if (hitPoints <= 0) {
			health.setText("DESTORYED");
			currentlyProducing.setText("CANNOT PRODUCE");
			turnsTillExport.setText("Cannot rebuild");
		}
	}

}

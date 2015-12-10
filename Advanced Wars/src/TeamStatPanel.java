import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TeamStatPanel extends JPanel {
	private int numberOfKills = 0;

	public InfoLabel kills = new InfoLabel("Number of kills: ");
	public InfoLabel tank = new InfoLabel("Number of Tank: ");
	public InfoLabel infantry = new InfoLabel("Number of Infantry: ");
	public InfoLabel artillery = new InfoLabel("Number of Artillery: ");
	public InfoLabel helicopter = new InfoLabel("Number of Helicopter: ");
	public JButton endTurn = new JButton("End Turn");
	public String team;

	public TeamStatPanel(String team) {

		this.setTeam(team);
		this.setPreferredSize(new Dimension(200, 644));

		this.setLayout(new GridLayout(0, 1));
		kills.updateText("0");

		this.add(kills);

		this.add(tank);
		this.add(artillery);
		this.add(infantry);
		this.add(helicopter);
		this.add(endTurn);
		endTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				TurnPanelLabel.endTurn();
				Unit.resetUnits();
				Building.loopThroughProduction();
				Building.regeneration();

			}
		});

	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public String getTeam() {
		return team;
	}

	public void increaseNumberOfKills() {
		numberOfKills++;
		this.kills.updateText(Integer.toString(numberOfKills));

	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void updateUnitInfo() {
		int tank = 0;
		int artillery = 0;
		int infantry = 0;
		int helicopter = 0;

		for (Unit x : Unit.UnitsArray) {
			if (x.getTeam() == team) {
				if (x.getType() == "TANK") {
					tank++;
					System.out.println(x.getTeam() + "," + x.getType() + "," + tank);
				} else if (x.getType() == "ARTILLERY") {
					artillery++;
				} else if (x.getType() == "INFANTRY") {
					infantry++;
				} else if (x.getType() == "HELICOPTER") {
					helicopter++;
				}
			} else {

			}
		}
		this.tank.updateText(Integer.toString(tank));
		this.artillery.updateText(Integer.toString(artillery));
		this.infantry.updateText(Integer.toString(infantry));
		this.helicopter.updateText(Integer.toString(helicopter));

		tank = 0;
		artillery = 0;
		infantry = 0;
		helicopter = 0;

	}
}

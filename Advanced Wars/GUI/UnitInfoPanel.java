import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UnitInfoPanel extends JPanel implements MouseListener {

	private InfoLabel health;
	private InfoLabel attack;
	private InfoLabel movement;
	private InfoLabel range;
	private InfoLabel type;
	private JButton attackEnemy;
	private String team;

	private Unit unit;

	private JButton capture;

	/**
	 * holds and displays an individual Units information on the Team
	 * Information Container
	 * 
	 * @param unit
	 *            (Unit that's information will be held and displayed)
	 */
	public UnitInfoPanel(Unit unit) {
		this.unit = unit;
		this.team = unit.getTeam();
		this.setPreferredSize(new Dimension(200, 644));

		this.setLayout(new GridLayout(0, 1));

		type = new InfoLabel("Unit: ");
		this.add(type);
		health = new InfoLabel("HEALTH: ");
		this.add(health);
		attack = new InfoLabel("Damage: ");
		this.add(attack);
		range = new InfoLabel("Attack Range: ");
		this.add(range);
		movement = new InfoLabel("Movement: ");
		this.add(movement);
		attackEnemy = new JButton("Attack!");
		this.add(attackEnemy);
		capture = new JButton("Capture");
		if (unit.getType().equals("INFANTRY")) {
			this.add(capture);
		}

		capture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Terrain.restoreAllTileStatus();
				PathFinders.capture(unit.getxPosition(), unit.getyPosition());
			}
		});

		attackEnemy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (Unit.selectedUnit != null) {
					if (!Unit.selectedUnit.isAttacked()) {
						Terrain.restoreAllTileStatus();
						PathFinders.selectedUnitAttack();
					}
				}
			}
		});

		this.setVisible(true);
	}

	public String getTeam() {
		return team;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void updateHealth() {
		this.health.updateText(Integer.toString(unit.info.health));
	}

	public void updateToPanel() {
		this.attack.updateText(Integer.toString(this.unit.info.attack));
		this.type.updateText(unit.getType());
		this.health.updateText(Integer.toString(this.unit.info.health));
		this.movement.updateText(Integer.toString(this.unit.info.movement));
		this.range.updateText(Integer.toString(this.unit.info.range));
	}

}

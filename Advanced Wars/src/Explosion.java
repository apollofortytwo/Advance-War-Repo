import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import layout.TableLayout;

public class Explosion extends JLabel {
	public static Explosion explosion = new Explosion();
	private static ActionListener explode;
	private static int count;
	private static Timer timer;

	/**
	 * Adds and explosion sprite on the location of a Building.
	 * 
	 * @param unit
	 *            (The unit that is being attacked)
	 */
	public static void explode(Building building) {
		Interface.attackingSpritePanel.add(explosion,
				Integer.toString(building.getxPosition()) + "," + Integer.toString(building.getyPosition()));
		count = 0;
		timer = new Timer(100, explode);
		timer.start();
	}

	/**
	 * Adds and explosion sprite on the location of a Unit.
	 * 
	 * @param unit
	 *            (The unit that is being attacked)
	 */
	public static void explode(Unit unit) {
		Interface.attackingSpritePanel.add(explosion,
				Integer.toString(unit.getxPosition()) + "," + Integer.toString(unit.getyPosition()));
		count = 0;
		timer = new Timer(100, explode);
		timer.start();
	}

	public static void explodeAdding() {

		explode = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				count++;

				if (count == 3) {
					Interface.attackingSpritePanel.removeAll();
					Interface.attackingSpritePanel.setLayout(new TableLayout(Application.size));
					Interface.attackingSpritePanel.revalidate();
					Interface.attackingSpritePanel.repaint();
					timer.stop();
				}
			}

		};
	}

	private Image sprite;

	public Explosion() {
		sprite = new ImageIcon(getClass().getResource("Explosion.png")).getImage();
		this.setIcon(new ImageIcon(sprite));
		explodeAdding();
	}

}

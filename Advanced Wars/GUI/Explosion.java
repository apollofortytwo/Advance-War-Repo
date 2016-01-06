import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import layout.TableLayout;

public class Explosion {

	private ActionListener explode;
	private Timer timer;
	int count = 0;

	public Explosion(int x, int y) {
		sprite = new ImageIcon(getClass().getResource("Explosion.png"))
				.getImage();

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(sprite));
		count = 0;
		Audio attackSound = new Audio("Attacking_Sound.wav");
		attackSound.getAudio().start();

		Interface.attackingSpritePanel.add(label, Integer.toString(x) + ","
				+ Integer.toString(y));

		ActionListener explode = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				count++;

				if (count == 2) {
					Interface.attackingSpritePanel.remove(label);
					Interface.attackingSpritePanel.setLayout(new TableLayout(
							TablePanel.size));
					Interface.attackingSpritePanel.revalidate();
					Interface.attackingSpritePanel.repaint();
					timer.stop();
				}
			}

		};

		timer = new Timer(100, explode);
		timer.start();

	}

	private Image sprite;

}

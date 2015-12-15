import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * Coupled with any game Entity Displays the health of the entity on the sprite
 * 
 * @author ApolloFortyTwo
 *
 */
public class HealthLabel extends JLabel {
	private int green, yellow, red;

	public HealthLabel(int health) {
		green = (int) (health / 1.2);
		yellow = (int) (health / 1.5);
		red = health / 3;
		this.updateHealth(health);
		this.setFont(new Font("Serif", Font.BOLD, 12));
		this.setBounds(0, 20, 15, 15);

	}

	public void reset(int health) {
		green = (int) (health / 1.2);
		yellow = (int) (health / 1.5);
		red = health / 3;
		this.updateHealth(health);
	}

	public int getGreen() {
		return green;
	}

	public int getRed() {
		return red;
	}

	public int getYellow() {
		return yellow;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public void setYellow(int yellow) {
		this.yellow = yellow;
	}

	public void updateHealth(int health) {
		this.setText(Integer.toString(health));
		if (health >= green) {
			this.setForeground(Color.green);
		} else if (health >= yellow) {
			this.setForeground(Color.yellow);
		} else if (health <= red) {
			this.setForeground(Color.red);
		}
	}

}

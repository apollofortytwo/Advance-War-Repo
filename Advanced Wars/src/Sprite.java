import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;

public class Sprite {
	Image idle, grayyed, destroyed;
	Image natural, blue, red;

	public Sprite(String team) {
		idle = new ImageIcon(getClass().getResource(team + "_Building" + ".png")).getImage();
		grayyed = new ImageIcon(getClass().getResource(team + "_Building_Destroyed" + ".png")).getImage();
	}

	public Sprite(String team, String unit) {
		String unitName = unit.charAt(0) + (unit.substring(1, unit.length())).toLowerCase();
		idle = new ImageIcon(getClass().getResource(team + unitName + ".png")).getImage();
		grayyed = new ImageIcon(getClass().getResource(team + unitName + "_Sleep.png")).getImage();
	}

	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getHeight(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getProperty(String name, ImageObserver observer) {
		// TODO Auto-generated method stub
		return null;
	}

	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getWidth(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 0;
	}
}

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;

public class SpriteLoader extends Image {

	public static Image getSprite(String path) {
		Image image = null;
		try {
			image = new ImageIcon(SpriteLoader.class.getResource(path + ".png"))
					.getImage();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("CAN't find" + path + ".png");
		}
		return image;

	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight(ImageObserver arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 0;
	}

}

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Terrain extends JLabel implements MouseListener {

	public static Terrain[][] terrainArray;

	/**
	 * Fills all cells on tilePanel with a certain type of Terrain
	 * 
	 * @param type
	 *            (the type of terrain used to fill the screen)
	 */
	public static void fillScreenWithTerrain(String type) {
		for (int x = 0; x < Application.WIDTH; x++) {
			for (int y = 0; y < Application.HEIGHT; y++) {
				Terrain.terrainArray[x][y] = new Terrain(x, y, type);
				Interface.tilePanel.add(Terrain.terrainArray[x][y],
						Integer.toString(x) + "," + Integer.toString(y));
			}
		}
	}

	/**
	 * resets the actions on all tiles
	 */
	public static void restoreAllTileStatus() {
		for (int x = 0; x < Application.WIDTH; x++) {
			for (int y = 0; y < Application.HEIGHT; y++) {
				Terrain.terrainArray[x][y].restore();
			}
		}
		Interface.tilePanel.revalidate();
		Interface.tilePanel.repaint();
	}

	private String type;
	Image terrainSprite, terrainSpriteBlue, terrainSpriteRed;
	private int xPosition, yPosition;
	public boolean isObstacle = false;
	public boolean isMoveable = false;
	public boolean isAttackable = false;

	public boolean isWater = false;

	public boolean isCapturable = false;

	/**
	 * JLabel that is added to the cell of tilePanel Terrain has an image and a
	 * set of qualities for the Terrain Terrain can change it's color to
	 * indicate it's action - Red (Unit can attack another Unit on this terrain
	 * location) - Blue (Unit can move onto this location)
	 * 
	 * types of Terrains - Grass - Concrete - Mud/Water (Reduces the Movement of
	 * Units by 2) - Tree (Units can move over this terrain. Unless flying)
	 * 
	 * @param x
	 *            (X Position on the tile Panel)
	 * @param y
	 *            (Y Position on the tile Panel)
	 * @param type
	 *            (Type of Terrain being Created)
	 */
	public Terrain(int x, int y, String type) {

		this.type = type;
		// this.setPreferredSize(new Dimension(32, 32));

		setXPosition(x);
		setYPosition(y);
		
		if(type.equals("Trees")){
			this.isObstacle = true;
		}

		if(type.equals("Water")){
			this.isWater = true;
		}
		
		this.terrainSprite = SpriteLoader.getSprite(type);
		this.terrainSpriteBlue = SpriteLoader.getSprite(type + "_Blue");
		this.terrainSpriteRed = SpriteLoader.getSprite(type + "_Red");

		this.setIcon(new ImageIcon(terrainSprite));

		this.addMouseListener(this);

		Interface.tilePanel.add(this,
				Integer.toString(x) + "," + Integer.toString(y));

	}

	public void resizeSprites(int width, int height) {
		// sprites.terrain = sprites.terrain.getScaledInstance(width, height,
		// Image.SCALE_DEFAULT);

		Interface.tilePanel.remove(this);

		Interface.tilePanel.add(this, Integer.toString(this.xPosition) + ","
				+ Integer.toString(this.yPosition));
		this.setIcon(new ImageIcon(terrainSprite));

		Interface.tilePanel.revalidate();
		Interface.tilePanel.repaint();

	}

	
	public boolean getIsAttackable() {
		return isAttackable;
	}

	public String getType() {
		return type;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	/**
	 * Allow for unit to move on this Terrains location
	 */
	public void highlight() {
		this.setIcon(new ImageIcon(this.terrainSpriteBlue));
		isMoveable = true;
		Interface.unitPanel.revalidate();
		Interface.unitPanel.repaint();
	}

	/**
	 * Capture (Can convert a neutral Building on Terrains location) Attack (Can
	 * attack enemy Unit on this Terrains location )
	 * 
	 * @param action
	 *            (CAPTURE OR ATTACK )
	 */
	public void highlight(String action) {

		if (!this.isMoveable) {
			this.setIcon(new ImageIcon(this.terrainSpriteRed));
		}
		if (action.equals("CAPTURE")) {
			setCapturable(true);
		} else if (action.equals("ATTACK")) {
			isAttackable = true;
		} else {

		}

		Interface.unitPanel.revalidate();
		Interface.unitPanel.repaint();
	}

	public boolean isCapturable() {
		return isCapturable;
	}

	public boolean isWater() {
		return isWater;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (this.isMoveable) {
			Unit.selectedUnit.moveUnit(xPosition, yPosition);
		} else {
			Terrain.restoreAllTileStatus();
			Interface.restore();
		}

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

	/**
	 * take the ability to do actions with Units off this terrain
	 */
	private void restore() {
		if (this.getIcon() != this.terrainSprite) {
			this.setIcon(new ImageIcon(this.terrainSprite));
			this.isMoveable = false;
			this.isCapturable = false;
			this.isAttackable = false;
		}
	}

	public void setCapturable(boolean isCapturable) {
		this.isCapturable = isCapturable;
	}

	public void setIsAttackable(boolean isAttackable) {
		this.isAttackable = isAttackable;
	}

	public void setWater(boolean isWater) {
		this.isWater = isWater;
	}

	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}

}

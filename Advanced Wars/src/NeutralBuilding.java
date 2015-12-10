

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class NeutralBuilding extends JLabel implements MouseListener {
	public static List<NeutralBuilding> buildingsArray = new ArrayList<NeutralBuilding>();

	public BuildingInfoPanel buildingPanel;

	private int xPosition;
	private int yPosition;

	private Image neutralTeam;

	public NeutralBuilding(int x, int y) {
		buildingsArray.add(this);

		xPosition = x;
		yPosition = y;

		neutralTeam = new ImageIcon(getClass().getResource("Building.png")).getImage();

		this.setIcon(new ImageIcon(neutralTeam));
		this.addMouseListener(this);

		Interface.tilePanel.add(this, Integer.toString(x) + "," + Integer.toString(y));
		Interface.tilePanel.repaint();
		Interface.tilePanel.revalidate();
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (Terrain.terrainArray[getXPosition()][getYPosition()].isCapturable()) {
			Building.buildingsArray
					.add((new CapturedBuilding(this.xPosition, this.yPosition, Unit.selectedUnit.getTeam())));

			NeutralBuilding.buildingsArray.remove(this);
			Interface.tilePanel.remove(this);
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

}

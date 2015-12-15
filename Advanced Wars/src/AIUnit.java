import java.util.ArrayList;
import java.util.List;

import layout.TableLayout;

public class AIUnit extends Unit {

	public static List<AIUnit> aiUnits = new ArrayList<AIUnit>();
	private Unit bestEnemy;
	private int turnsToReachEnemy;
	private int movetoX, movetoY;
	
	public AIUnit(int xPosition, int yPosition, String unit, String team) {
		super(xPosition, yPosition, unit, team);
		aiUnits.add(this);
		
	}
	
	public static void moveAIs(){
		System.out.println("MOVE AI");
		
		for(AIUnit x: aiUnits){
			x.closestEnemy();
		}
		
		for(AIUnit x: aiUnits){
			PathFinderAI.move(x, x.getxPosition(),x.getyPosition(), x.info.movement);
		}
		
		for(AIUnit x: aiUnits){
			x.move();
		}
	}
	
	private void closestEnemy(){
		setBestEnemy(null);
		
		turnsToReachEnemy = 100;
		for(Unit x: Unit.UnitsArray){
			int covered = 0;
			
			if(x.getEnemy().equals(this.getTeam())){
				if(turnsToReachEnemy >= Math.abs(this.getxPosition() - x.getxPosition()) + Math.abs(this.getyPosition() - x.getyPosition())){
					turnsToReachEnemy = Math.abs(this.getxPosition() - x.getxPosition()) + Math.abs(this.getyPosition() - x.getyPosition());
					System.out.println(this.turnsToReachEnemy);
					setBestEnemy(x);
					System.out.println("Unit has targeted: "+this.bestEnemy.getxPosition() +","+ this.bestEnemy.getyPosition());
				}else{
					System.out.println("NOT BETTER");
				}
			}
		}
	}
	
	public void bestMove(int x, int y){
		
		if(turnsToReachEnemy >= Math.abs(x - this.bestEnemy.getxPosition()) + Math.abs(y - this.bestEnemy.getyPosition())){
			turnsToReachEnemy = Math.abs(x - this.bestEnemy.getxPosition()) + Math.abs(y - this.bestEnemy.getyPosition());
			System.out.println("Considering to move to: "+ x +","+y);
			System.out.println(this.turnsToReachEnemy);
			this.setMovetoX(x);
			this.setMovetoY(y);
		}
	}
	
	private void move(){
		Interface.unitPanel.removeAll();
		Interface.unitPanel.setLayout(new TableLayout(Application.size));
		this.setCordinates(this.movetoX, this.movetoY);
		
		System.out.println("Unit is moving to: "+this.movetoX +", "+ this.movetoY);
		
		for (int i = 0; i < Unit.UnitsArray.size(); i++) {
			Interface.unitPanel.add(Unit.UnitsArray.get(i), Unit.UnitsArray
					.get(i).getxPosition(), Unit.UnitsArray.get(i)
					.getyPosition());
		}
		this.setMoved(true);
		Interface.addUnitInfo(this);
		
		Interface.unitPanel.revalidate();
		Interface.unitPanel.repaint();
	}

	public Object getBestEnemy() {
		return bestEnemy;
	}

	public void setBestEnemy(Unit bestEnemy) {
		this.bestEnemy = bestEnemy;
	}

	public int getMovetoX() {
		return movetoX;
	}

	public void setMovetoX(int movetoX) {
		this.movetoX = movetoX;
	}

	public int getMovetoY() {
		return movetoY;
	}

	public void setMovetoY(int movetoY) {
		this.movetoY = movetoY;
	}

}

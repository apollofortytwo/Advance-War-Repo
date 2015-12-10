

import java.io.Serializable;

public class UnitInfo implements Serializable {
	public static int getProductionTime(String unit) {
		if (unit.equals("TANK")) {
			return 10;
		} else if (unit.equals("ARTILLERY") ) {
			return 15;
		} else if (unit.equals("INFANTRY")) {
			return 3;
		}else if (unit.equals("HELICOPTER")) {
			return 8;
		} else {
			return 100;
		}
	}
	int health, attack, movement, range;

	boolean flying;

	public UnitInfo(String unit) {
		this.health = this.getUnitHealthInfo(unit);
		this.attack = this.getUnitAttackInfo(unit);
		this.movement = this.getUnitMovementInfo(unit);
		this.range = this.getUnitRangeInfo(unit);
	}

	public static int getUnitAttackInfo(String unit) {
		if (unit.equals("TANK")) {
			return 10;
		} else if (unit.equals("ARTILLERY") ) {
			return 5;
		} else if (unit.equals("INFANTRY")) {
			return 5;
		}else if (unit.equals("HELICOPTER")) {
			return 5;
		} else {
			return 0;
		}
	}


	public static int getUnitHealthInfo(String unit) {
		if (unit.equals("TANK")) {
			return 20;
		} else if (unit.equals("ARTILLERY") ) {
			return 10;
		} else if (unit.equals("INFANTRY")) {
			return 15;
		}else if (unit.equals("HELICOPTER")) {
			return 10;
		} else {
			return 0;
		}
	}

	public static int getUnitMovementInfo(String unit) {
		if (unit.equals("TANK")) {
			return 3;
		} else if (unit.equals("ARTILLERY") ) {
			return 3;
		} else if (unit.equals("INFANTRY")) {
			return 5;
		}else if (unit.equals("HELICOPTER")) {
			return 2;
		} else {
			return 0;
		}
	}

	public static int getUnitRangeInfo(String unit) {
		if (unit.equals("TANK")) {
			return 3;
		} else if (unit.equals("ARTILLERY") ) {
			return 2;
		} else if (unit.equals("INFANTRY")) {
			return 3;
		}else if (unit.equals("HELICOPTER")) {
			return 5;
		} else {
			return 0;
		}
	}

}

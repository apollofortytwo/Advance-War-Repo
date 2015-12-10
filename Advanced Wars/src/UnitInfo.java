

import java.io.Serializable;

public class UnitInfo implements Serializable {
	public static int getProductionTime(String unit) {
		if (unit == "TANK") {
			return 10;
		} else if (unit == "ARTILLERY") {
			return 15;
		} else if (unit == "INFANTRY") {
			return 3;
		} else if (unit == "ENGINNER") {
			return 1;
		} else if (unit == "HELICOPTER") {
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

	public int getUnitAttackInfo(String Unit) {
		if (Unit == "TANK") {
			return 10;
		} else if (Unit == "INFANTRY") {
			return 5;
		} else if (Unit == "HELICOPTER") {
			return 5;
		} else if (Unit == "ARTILLERY") {
			return 5;
		}
		return 0;
	}

	public int getUnitHealthInfo(String Unit) {
		if (Unit == "TANK") {
			return 20;
		} else if (Unit == "INFANTRY") {
			return 10;
		} else if (Unit == "HELICOPTER") {
			return 15;
		} else if (Unit == "ARTILLERY") {
			return 10;
		}
		return 0;
	}

	public int getUnitMovementInfo(String Unit) {
		if (Unit == "TANK") {
			return 3;
		} else if (Unit == "INFANTRY") {
			return 3;
		} else if (Unit == "HELICOPTER") {
			return 5;
		} else if (Unit == "ARTILLERY") {
			return 2;
		}
		return 0;
	}

	public int getUnitRangeInfo(String Unit) {
		if (Unit == "TANK") {
			return 3;
		} else if (Unit == "INFANTRY") {
			return 2;
		} else if (Unit == "HELICOPTER") {
			return 3;
		} else if (Unit == "ARTILLERY") {
			return 5;
		}
		return 0;
	}

}

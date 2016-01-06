import java.io.Serializable;

public class UnitInfo implements Serializable {

	public static int getProductionTime(String unit) {
		if (unit.equals("TANK")) {
			return 10;
		} else if (unit.equals("ARTILLERY")) {
			return 15;
		} else if (unit.equals("INFANTRY")) {
			return 3;
		} else if (unit.equals("HELICOPTER")) {
			return 8;
		} else {
			return 100;
		}
	}

	public static int getUnitAttackInfo(String unit) {
		if (unit.equals("TANK")) {
			return 10;
		} else if (unit.equals("ARTILLERY")) {
			return 5;
		} else if (unit.equals("INFANTRY")) {
			return 5;
		} else if (unit.equals("HELICOPTER")) {
			return 5;
		} else {
			return 0;
		}
	}

	public static int getUnitHealthInfo(String unit) {
		if (unit.equals("TANK")) {
			return 20;
		} else if (unit.equals("ARTILLERY")) {
			return 10;
		} else if (unit.equals("INFANTRY")) {
			return 15;
		} else if (unit.equals("HELICOPTER")) {
			return 10;
		} else {
			return 0;
		}
	}

	public static int getUnitMovementInfo(String unit) {
		if (unit.equals("TANK")) {
			return 3;
		} else if (unit.equals("ARTILLERY")) {
			return 3;
		} else if (unit.equals("INFANTRY")) {
			return 3;
		} else if (unit.equals("HELICOPTER")) {
			return 5;
		} else {
			return 0;
		}
	}

	public static int getUnitRangeInfo(String unit) {
		if (unit.equals("TANK")) {
			return 3;
		} else if (unit.equals("ARTILLERY")) {
			return 7;
		} else if (unit.equals("INFANTRY")) {
			return 3;
		} else if (unit.equals("HELICOPTER")) {
			return 3;
		} else {
			return 0;
		}
	}

	int health, attack, movement, range;

	boolean flying;

	/**
	 * gives unit the information that is followed by it's type
	 * 
	 * @param unit
	 *            (the unit that gets it's information)
	 */
	public UnitInfo(String unit) {
		this.health = UnitInfo.getUnitHealthInfo(unit);
		this.attack = UnitInfo.getUnitAttackInfo(unit);
		this.movement = UnitInfo.getUnitMovementInfo(unit);
		this.range = UnitInfo.getUnitRangeInfo(unit);
	}

}

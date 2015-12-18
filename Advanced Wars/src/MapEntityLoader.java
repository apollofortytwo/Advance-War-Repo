
public class MapEntityLoader {

	MapEntityLoader(String map) {
		if (map.equals("MOBA")) {
			moba();
		}
	}
	public static boolean ai = false;
	

	private void moba() {
		if(ai){
			/*
			new AIUnit(17, 1, "TANK", "Red");
			new AIUnit(18, 2, "TANK", "Red");
			new AIUnit(16, 1, "INFANTRY", "Red");
			new AIUnit(18, 3, "INFANTRY", "Red");
			new AIUnit(16, 3, "INFANTRY", "Red");
			new AIUnit(17, 2, "INFANTRY", "Red");
			new AIUnit(15, 2, "HELICOPTER", "Red");
			new AIUnit(17, 4, "HELICOPTER", "Red");
			*/
			new AIUnit(18, 1, "ARTILLERY", "Red");
			
		}else{
			new Unit(17, 1, "TANK", "Red");
			new Unit(18, 2, "TANK", "Red");
			new Unit(16, 1, "INFANTRY", "Red");
			new Unit(18, 3, "INFANTRY", "Red");
			new Unit(16, 3, "INFANTRY", "Red");
			new Unit(17, 2, "INFANTRY", "Red");
			new Unit(15, 2, "HELICOPTER", "Red");
			new Unit(17, 4, "HELICOPTER", "Red");
			new Unit(18, 1, "ARTILLERY", "Red");
		}

		/*
		new Unit(1, 17, "TANK", "Blue");
		new Unit(2, 18, "TANK", "Blue");
		new Unit(1, 16, "INFANTRY", "Blue");
		new Unit(3, 18, "INFANTRY", "Blue");
		new Unit(3, 16, "INFANTRY", "Blue");
		new Unit(2, 17, "INFANTRY", "Blue");
		*/
		new Unit(2, 15, "HELICOPTER", "Blue");
		new Unit(4, 17, "HELICOPTER", "Blue");
		
		new Unit(1, 18, "ARTILLERY", "Blue");

		int[] xRedBuilding = { 17, 19, 19 };
		int[] yRedBuilding = { 0, 0, 2 };

		for (int i = 0; i != xRedBuilding.length; i++) {

			if (Terrain.terrainArray[xRedBuilding[i]][yRedBuilding[i]] != null) {
				Interface.tilePanel.remove(Terrain.terrainArray[xRedBuilding[i]][yRedBuilding[i]]);
			}
			new Building(xRedBuilding[i], yRedBuilding[i], "Red");
		}
		int[] xBlueBuilding = { 0, 0, 2 };
		int[] yBlueBuilding = { 17, 19, 19 };

		for (int i = 0; i != xBlueBuilding.length; i++) {
			new Building(xBlueBuilding[i], yBlueBuilding[i], "Blue");
		}

		int[] xNeutralBuilding = { 2, 7, 12, 17 };
		int[] yNeutralBuilding = { 2, 5, 14, 17 };

		for (int i = 0; i != xNeutralBuilding.length; i++) {
			new NeutralBuilding(xNeutralBuilding[i], yNeutralBuilding[i]);
		}
	}

	public static void mobaNeutralBuilding() {
		int[] xNeutralBuilding = { 2, 7, 12, 17 };
		int[] yNeutralBuilding = { 2, 5, 14, 17 };

		for (int i = 0; i != xNeutralBuilding.length; i++) {
			for (Building x : Building.buildingsArray) {
				if (x.getxPosition() != xNeutralBuilding[i] && x.getyPosition() != yNeutralBuilding[i]) {
					new NeutralBuilding(xNeutralBuilding[i], yNeutralBuilding[i]);
				}
			}

		}
	}
}

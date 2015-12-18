public class PathFinderAI extends PathFinders {

	public static void move(AIUnit unit, int x, int y, int distance) {
		if (distance > 0) {
			try {
				if (!Terrain.terrainArray[x + 1][y].isObstacle) {
					if (Terrain.terrainArray[x + 1][y].isWater()) {
						unit.potentialMove(x + 1, y);
						move(unit, x + 1, y, distance - 2);

					} else {
						unit.potentialMove(x + 1, y);
						move(unit, x + 1, y, distance - 1);

					}
				}
			} catch (Exception e) {

			}
			try {
				if (!Terrain.terrainArray[x - 1][y].isObstacle) {
					if (Terrain.terrainArray[x - 1][y].isWater()) {
						unit.potentialMove(x - 1, y);
						move(unit, x - 1, y, distance - 2);

					} else {
						unit.potentialMove(x - 1, y);
						move(unit, x - 1, y, distance - 1);

					}
				}
			} catch (Exception e) {

			}
			try {
				if (!Terrain.terrainArray[x][y - 1].isObstacle) {
					if (Terrain.terrainArray[x][y - 1].isWater()) {
						unit.potentialMove(x, y - 1);
						move(unit, x, y - 1, distance - 2);

					} else {
						unit.potentialMove(x, y - 1);
						move(unit, x, y - 1, distance - 1);
					}
				}
			} catch (Exception e) {

			}
			try {
				if (!Terrain.terrainArray[x][y + 1].isObstacle) {
					if (Terrain.terrainArray[x][y + 1].isWater()) {
						unit.potentialMove(x, y + 1);
						move(unit, x, y + 1, distance - 2);
					} else {
						unit.potentialMove(x, y + 1);
						move(unit, x, y + 1, distance - 1);

					}
				}
			} catch (Exception e) {
			}
		} else {
			unit.potentialMove(x, y);
			return;
		}
	}
	
	
}

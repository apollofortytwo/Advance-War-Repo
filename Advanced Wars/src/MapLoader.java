public class MapLoader {

	private static String map;

	public static String getMap() {
		return map;
	}

	public MapLoader() {

		if (getMap().equals("MOBA")) {

			System.out.println("LOADING MOBA");

			Terrain.fillScreenWithTerrain("GRASS");

			int[] xTree = { 1, 1, 3, 3, 5, 6, 8, 9, 10, 12, 13, 14, 13, 12, 10, 11, 8, 7, 6, 8, 3, 2, 2, 3, 2, 2, 2, 2,
					2, 2, 5, 6, 7, 6, 7, 8, 9, 9, 10, 11, 11, 11, 12, 13, 13, 14, 18, 16, 18, 16, 11, 10, 12, 12, 4, 5,
					4, 4, 7, 7, 5, 5, 8, 9, 17, 16, 17, 16, 17, 17, 17, 15, 14, 15, 15, 17, 17, 17, 14, 14 };
			int[] yTree = { 1, 3, 1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 5, 4, 4, 4, 5, 5, 5, 6, 8, 8, 9, 10, 12, 13,
					14, 17, 17, 17, 15, 15, 14, 16, 17, 17, 17, 15, 14, 15, 15, 17, 17, 18, 16, 16, 18, 12, 12, 10, 9,
					10, 11, 13, 12, 10, 9, 8, 7, 7, 7, 14, 14, 13, 11, 11, 10, 9, 9, 8, 7, 6, 6, 7, 5, 11, 12 };

			for (int i = 0; i != xTree.length; i++) {

				if (Terrain.terrainArray[xTree[i]][yTree[i]] != null) {
					Interface.tilePanel.remove(Terrain.terrainArray[xTree[i]][yTree[i]]);
				}
				Terrain.terrainArray[xTree[i]][yTree[i]] = new Terrain(xTree[i], yTree[i], "TREE");

			}

			int[] xWater = { 4, 4, 3, 5, 5, 4, 5, 6, 6, 7, 8, 7, 7, 8, 6, 6, 11, 11, 12, 12, 12, 13, 13, 13, 13, 14, 14,
					14, 15, 15, 16, 15 };
			int[] yWater = { 3, 4, 4, 4, 5, 5, 6, 5, 6, 6, 6, 7, 8, 8, 7, 8, 11, 13, 11, 12, 13, 13, 12, 11, 14, 13, 14,
					15, 15, 14, 15, 16 };

			for (int i = 0; i != xWater.length; i++) {

				if (Terrain.terrainArray[xWater[i]][yWater[i]] != null) {
					Interface.tilePanel.remove(Terrain.terrainArray[xWater[i]][yWater[i]]);
				}
				Terrain.terrainArray[xWater[i]][yWater[i]] = new Terrain(xWater[i], yWater[i], "WATER");

			}

			int[] xConcrete = { 18, 18, 18, 17, 19, 17, 17, 18, 19, 19, 18, 19, 17, 16, 15, 16, 16, 15, 16, 16, 15, 15,
					14, 14, 13, 18, 19, 1, 0, 1, 1, 2, 3, 4, 5, 6, 5, 4, 3, 4, 3, 2, 2, 1, 0, 0, 0, 0, 1, 2, 3, 3, 4 };
			int[] yConcrete = { 0, 2, 1, 1, 1, 2, 3, 3, 3, 4, 4, 5, 4, 4, 3, 3, 2, 2, 1, 0, 1, 0, 1, 0, 0, 5, 6, 19, 18,
					18, 17, 18, 19, 19, 19, 19, 18, 18, 18, 17, 17, 17, 16, 16, 16, 15, 14, 15, 15, 15, 15, 16, 16 };

			for (int i = 0; i != xConcrete.length; i++) {

				if (Terrain.terrainArray[xConcrete[i]][yConcrete[i]] != null) {
					Interface.tilePanel.remove(Terrain.terrainArray[xConcrete[i]][yConcrete[i]]);
				}
				Terrain.terrainArray[xConcrete[i]][yConcrete[i]] = new Terrain(xConcrete[i], yConcrete[i], "CONCRETE");

			}

			return;
		} else if (getMap() == "Large") {
			Application.size = new double[][] {
					{ 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32,
							32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 }, // Columns
					{ 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 } // Rows;
			};
			;
			Terrain.fillScreenWithTerrain("GRASS");
		}

	}

	/**
	 * @param map
	 *            the map to set
	 */
	public static void setMap(String map) {
		MapLoader.map = map;
		if (map.equals("MOBA")) {
			Application.size = new double[][] {
					{ 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 }, // Columns
					{ 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 } // Rows;
			};
			;
			Application.setTable();
			Terrain.terrainArray = new Terrain[Application.size[0].length][Application.size[1].length];
		}

	}
}

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import layout.SaveObject;

public class LoadGame {

	LoadGame() throws Exception {
		@SuppressWarnings("resource")
		ObjectInputStream os = new ObjectInputStream(new FileInputStream("Game Save.txt"));

		Save save = (Save) os.readObject();

		MapSelection.setMap("MOBA");
		Application.size = new double[][] {
				{ 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 }, // Columns
				{ 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 } // Rows;
		};
		;
		Application.start.dispose();

		Application.setTable();

		Terrain.terrainArray = new Terrain[Application.size[0].length][Application.size[1].length];

		Interface.frame();

		Interface.initalize();

		MapSelection.mapCreation();

		for (SaveObject x : save.getList()) {
			new Unit(x.x, x.y, x.type, x.team);
		}

		Interface.blueTeamInfo.updateUnitInfo();
		Interface.redTeamInfo.updateUnitInfo();

		TurnPanelLabel.turnPanelStart();

		Application.playMusic();

		Interface.mainFrame.setVisible(true);

		// os.close();

	}
}

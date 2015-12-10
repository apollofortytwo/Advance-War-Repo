import java.io.FileInputStream;
import java.io.ObjectInputStream;

import layout.SaveObject;

public class LoadGame {

	LoadGame() throws Exception {
		ObjectInputStream os = new ObjectInputStream(new FileInputStream("Game Save.txt"));

		Save save = (Save) os.readObject();

		MapLoader.setMap(save.map);
		
		Application.start.dispose();
		
		Interface.frame();

		Interface.initalize();
		
		new MapLoader();
		
		for (SaveObject x : save.getList()) {
			new Unit(x.x, x.y, x.type, x.team);
		}
		
		Interface.blueTeamInfo.updateUnitInfo();
		Interface.redTeamInfo.updateUnitInfo();

		TurnPanelLabel.turnPanelStart();

		Application.playMusic();

		os.close();

	}
}

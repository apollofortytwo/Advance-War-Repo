import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.sound.sampled.Clip;

public class LoadFile {
	public static File file;

	LoadFile() throws Exception {

		ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));

		Save save = (Save) os.readObject();

		MapLoader.setMap(save.map);

		Application.bgm = new Audio("war.wav");
		Application.bgm.getAudio().loop(Clip.LOOP_CONTINUOUSLY);

		Application.bgm.getAudio().start();

		Application.start.dispose();

		Interface.frame();

		Interface.initalize();

		new MapLoader();

		loadUnits(save);
		loadBuildings(save);

		MapEntityLoader.mobaNeutralBuilding();

		Interface.blueTeamInfo.updateUnitInfo();
		Interface.redTeamInfo.updateUnitInfo();

		TurnPanel.turnText = save.turnText;
		TurnPanel.turnsElapsed = save.turnsElapsed;

		TurnPanel.turnPanelStart();

		os.close();

	}

	private void loadUnits(Save save) {
		for (SaveObject x : save.getUnits()) {
			Unit t = new Unit(x.x, x.y, x.type, x.team);
			t.info.health = x.health;
			t.setMoved(x.moved);
			t.setAttacked(x.attacked);

			t.updateHealthLabel();
			t.infoPanel.updateHealth();

		}
	}

	private void loadBuildings(Save save) {
		for (SaveObject x : save.getBuildings()) {
			if (x.captured) {
				CapturedBuilding t = new CapturedBuilding(x.x, x.y, x.team);
				System.out.println("Captured Building: " + x.x + "," + x.y
						+ "," + x.team);
				t.health = x.health;
				t.setCurrentlyProducing(x.currentlyProduction);
				t.setEndTimeofProduction(x.productionTime);

				t.buildingPanel.updateInfo();
				t.healthLabel.updateHealth(t.health);
			} else {
				Building t = new Building(x.x, x.y, x.team);
				t.Captured = x.captured;
				t.health = x.health;
				t.setCurrentlyProducing(x.currentlyProduction);
				t.setEndTimeofProduction(x.productionTime);

				t.buildingPanel.updateInfo();
				t.healthLabel.updateHealth(t.health);
			}
		}
	}

}

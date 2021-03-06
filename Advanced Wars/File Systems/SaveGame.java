import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

public class SaveGame extends JFrame {
	public static File fileName;

	public SaveGame() throws IOException {
		for (Unit x : Unit.UnitsArray) {
			new SaveObject(x);
		}
		for (Building x : Building.buildingsArray) {
			new SaveObject(x);
		}

		Save save = new Save();
		save.map = MapLoader.getMap();
		save.size = TablePanel.size;
		save.turnsElapsed = TurnPanel.turnsElapsed;
		save.turnText = TurnPanel.turnText;

		save.setUnits(SaveObject.units);
		save.setBuildings(SaveObject.buildings);
		save.setNBuildings(SaveObject.buildings);

		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream(fileName + ".save"));
			os.writeObject(save);
			os.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Done");
	}

}

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class SaveGame {

	public SaveGame() throws IOException {
		File fileName = new File("Game Save.txt");

		for (Unit x : Unit.UnitsArray) {
			new SaveObject(x);
		}
		for(Building x: Building.buildingsArray){
			new SaveObject(x);
		}

		Save save = new Save();
		save.map = MapLoader.getMap();
		save.size = Application.size;
		save.turnsElapsed = TurnPanel.turnsElapsed;
		save.turnText = TurnPanel.turnText;
		
		save.setUnits(SaveObject.units);
		save.setBuildings(SaveObject.buildings);
		save.setNBuildings(SaveObject.buildings);
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeObject(save);
			os.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Done");
	}
}

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import layout.SaveObject;

public class SaveGame {

	public SaveGame() throws IOException {
		File fileName = new File("Game Save.txt");

		for (Unit x : Unit.UnitsArray) {
			new SaveObject(x.getxPosition(), x.getyPosition(), x.info.health, x.getTeam(), x.getType());
		}

		Save save = new Save();
		save.map = MapSelection.getMap();
		save.size = Application.size;

		save.setList(SaveObject.Units);

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

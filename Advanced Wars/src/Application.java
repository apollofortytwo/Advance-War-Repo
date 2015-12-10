
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl; 
/**
 * 									Advanced Wars
 * Game:		
 * 		Move units and destroy the other players Bases while you protect your own.
 * 		Grid style, Turn-Based, Strategy game
 * 
 * @author Bryan
 *
 */
public class Application {
	public static double[][] size;
	public static int WIDTH, HEIGHT;
	public static StartScreen start;

	public static void attackingSound() {
		try {
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(Application.class.getResource("Attacking_Sound.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-25.0f);
			clip.start();
		} catch (Exception e) {
		}
	}

	public static void game() {
		Interface.frame();

		Interface.initalize();

		MapSelection.mapCreation();

		new Unit(17, 1, "TANK", "Red");
		new Unit(18, 2, "TANK", "Red");
		new Unit(16, 1, "INFANTRY", "Red");
		new Unit(18, 3, "INFANTRY", "Red");
		new Unit(16, 3, "INFANTRY", "Red");
		new Unit(17, 2, "INFANTRY", "Red");
		new Unit(15, 2, "HELICOPTER", "Red");
		new Unit(17, 4, "HELICOPTER", "Red");
		new Unit(18, 1, "ARTILLERY", "Red");

		new Unit(1, 17, "TANK", "Blue");
		new Unit(2, 18, "TANK", "Blue");
		new Unit(1, 16, "INFANTRY", "Blue");
		new Unit(3, 18, "INFANTRY", "Blue");
		new Unit(3, 16, "INFANTRY", "Blue");
		new Unit(2, 17, "INFANTRY", "Blue");
		new Unit(2, 15, "HELICOPTER", "Blue");
		new Unit(4, 17, "HELICOPTER", "Blue");
		new Unit(1, 18, "ARTILLERY", "Blue");

		Interface.blueTeamInfo.updateUnitInfo();
		Interface.redTeamInfo.updateUnitInfo();

		TurnPanelLabel.turnPanelStart();

		playMusic();

		try {
			new SaveGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		start = new StartScreen();
	}

	public static void playMusic() {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(Application.class.getResource("war.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-25.0f);
			clip.start();
		} catch (Exception e) {
		}
	}

	public static void setTable() {
		WIDTH = size[0].length;
		HEIGHT = size[1].length;
	}

}
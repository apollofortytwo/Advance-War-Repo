
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Advanced Wars Game: Move units and destroy the other players Bases while you
 * protect your own. Grid style, Turn-Based, Strategy game
 * 
 * @author ApolloFortyTwo
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
		System.out.println("Loading");

		start.dispose();

		Interface.frame();

		Interface.initalize();

		new MapLoader();

		new MapEntityLoader(MapLoader.getMap());

		Interface.blueTeamInfo.updateUnitInfo();
		Interface.redTeamInfo.updateUnitInfo();

		TurnPanel.endTurn();
		TurnPanel.turnPanelStart();

		playMusic();

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
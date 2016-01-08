import javax.sound.sampled.Clip;

/**
 * Advanced Wars Game: Move units and destroy the other players Bases while you
 * protect your own. Grid style, Turn-Based, Strategy game
 * 
 * @author ApolloFortyTwo
 *
 */
public class Application {

	public static void game() {
		start.dispose();

		Interface.frame();

		Interface.initalize();

		new MapLoader();

		new MapEntityLoader(MapLoader.getMap());

		Interface.blueTeamInfo.updateUnitInfo();
		Interface.redTeamInfo.updateUnitInfo();

		TurnPanel.endTurn();
		TurnPanel.turnPanelStart();

		bgm = new Audio("war.wav");
		bgm.getAudio().loop(Clip.LOOP_CONTINUOUSLY);

		bgm.getAudio().start();

	}

	public static void main(String[] args) {
		start = new StartScreen();
	}

	static Audio bgm;

	public static StartScreen start;

}
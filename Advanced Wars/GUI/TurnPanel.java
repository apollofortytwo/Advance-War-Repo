import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TurnPanel {
	/**
	 * Changes the turn so the other team can move
	 */
	public static void endTurn() {
		System.out.println(turnText);
		turnsElapsed++;
		countDownTimer = 101;
		if (turnText.equals("Blue")) {
			turnText = "Red";
			turnLabel.setBackground(Color.red);
		} else {
			turnText = "Blue";
			turnLabel.setBackground(Color.blue);
		}
		Terrain.restoreAllTileStatus();
		updateTurnLabel();
	}
	/**
	 * Sets the information for the first round gives the proper color to the
	 * current Turn and start's the time for the game
	 */
	public static void turnPanelStart() {
		turnLabel.setForeground(Color.white);
		updateTurnLabel();
		turnTimer();
		if (turnText.equals("Blue")) {
			turnLabel.setBackground(Color.blue);
		} else {
			turnLabel.setBackground(Color.red);
		}
	}
	/**
	 * starts the timer for the amount of time a player can do actions
	 */

	public static void turnTimer() {
		ActionListener time = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countDownTimer--;
				timeCounter.setText("TIME REMAINING: "
						+ Integer.toString(countDownTimer));
				if (countDownTimer <= 0) {
					countDownTimer = 100;

				}
			}
		};
		Timer count = new Timer(1000, time);
		count.start();
	}

	/**
	 * Displays the Information that the team can move Increases the amount of
	 * turns elapsed and displays it resets the count down timer
	 */
	private static void updateTurnLabel() {
		turnLabel.setText(turnText.toUpperCase());
		timeCounter.setText(Integer.toString(countDownTimer));
		turnsElapsedLabel.setText("TURN: " + Integer.toString(turnsElapsed));
		System.out.println("Current turn: " + turnText);
	}
	public static TurnPanelLabel turnLabel = new TurnPanelLabel();
	public static TurnPanelLabel timeCounter = new TurnPanelLabel();

	public static TurnPanelLabel turnsElapsedLabel = new TurnPanelLabel();

	public static String turnText = "Red";

	public static int turnsElapsed = 0;

	public static int countDownTimer = 100;

}

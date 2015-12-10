import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TurnPanelLabel extends JLabel {

	public static JButton endTurn = new JButton("End Turn");
	public static TurnPanelLabel turnLabel = new TurnPanelLabel();
	public static TurnPanelLabel timeCounter = new TurnPanelLabel();
	public static TurnPanelLabel turnsElapsedLabel = new TurnPanelLabel();

	public static String turnText = "Blue";
	public static int turnsElapsed = 0;
	public static int countDownTimer = 100;

	public static void endTurn() {
		if (turnText == "Blue") {
			turnText = "Red";
		} else {
			turnText = "Blue";
		}
		Terrain.restoreAllTileStatus();
		updateTurnLabel();
	}

	public static void turnPanelStart() {
		TurnPanelLabel.timeCounter.setText(Integer.toString(TurnPanelLabel.countDownTimer));
		TurnPanelLabel.turnLabel.setForeground(Color.white);
		TurnPanelLabel.updateTurnLabel();
		TurnPanelLabel.turnsElapsedLabel.setText("TURN: " + Integer.toString(TurnPanelLabel.turnsElapsed));
		TurnPanelLabel.turnTimer();
	}

	public static void turnTimer() {
		ActionListener time = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countDownTimer--;
				timeCounter.setText("TIME REMAINING: " + Integer.toString(countDownTimer));
				if (countDownTimer <= 0) {
					countDownTimer = 100;

				}
			}
		};
		Timer count = new Timer(1000, time);
		count.start();
	}

	public static void updateTurnLabel() {
		turnLabel.setText(turnText.toUpperCase());
		turnsElapsed++;
		turnsElapsedLabel.setText("TURN: " + Integer.toString(turnsElapsed));
		System.out.println(turnText);
		countDownTimer = 101;

		if (turnText == "Blue") {
			turnLabel.setBackground(Color.blue);
		} else if (turnText == "Red") {
			turnLabel.setBackground(Color.red);
		}
	}

	public TurnPanelLabel() {
		setPreferredSize(new Dimension(300, 25));
		setBorder(BorderFactory.createEtchedBorder());
		setOpaque(true);
		setFont(new Font("MS PGothic", Font.BOLD, 27));
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}

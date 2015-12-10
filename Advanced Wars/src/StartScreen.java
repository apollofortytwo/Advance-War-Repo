import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartScreen extends JFrame implements ActionListener {

	public StartScreen() {

		this.setSize(1080, 720);
		this.setLayout(null);
		this.setVisible(true);
		title();
		options();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "NEW") {
			MapSelection.mapSelection();
			this.dispose();
		} else {
			try {
				new LoadGame();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void options() {
		JPanel optionsPanel = new JPanel();
		GridLayout g = new GridLayout(2, 0);
		g.setVgap(50);

		optionsPanel.setLayout(g);
		optionsPanel.setBounds(200, 300, 680, 200);

		JButton newGame = new JButton("New Game");

		newGame.addActionListener(this);
		newGame.setActionCommand("NEW");

		optionsPanel.add(newGame);

		JButton loadGame = new JButton("load Game");
		loadGame.addActionListener(this);
		loadGame.setActionCommand("LOAD");

		optionsPanel.add(loadGame);

		this.add(optionsPanel, BorderLayout.CENTER);

	}

	public void title() {
		JLabel title = new JLabel("ADVANCED WARS");
		title.setFont(new Font("Serifs", Font.BOLD, 100));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBackground(Color.white);

		title.setBounds(0, 0, 1080, 100);
		this.add(title);

	}
}

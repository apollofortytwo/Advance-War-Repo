import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Start Screen: Gives the user the ability to start a new game or load a game
 * 
 * Map selection Screen: picks the map the user will play
 * 
 * @author Bryan
 *
 */
public class StartScreen extends JFrame implements ActionListener {

	public StartScreen() {
		this.setSize(1080, 720);
		this.setLayout(null);
		this.setExtendedState(Frame.MAXIMIZED_VERT);
		setup();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("NEW")) {
			mapSelect();
		} else if (arg0.getActionCommand().equals("LOAD")) {
			loadFile();
		}

	}

	private void loadFile() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ADVANCED WARS SAVE FILE", "save", "save");
		fc.setFileFilter(filter);
		fc.showOpenDialog(this);
		if (fc.getSelectedFile() != null) {
			LoadFile.file = fc.getSelectedFile();
			try {
				new LoadFile();
			} catch (Exception e) {
			}
		} else {
			return;
		}
	}

	private void mapSelect() {

		MapLoader.setMap("MOBA");

		selectGame();
	}

	private void options() {
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

	private void selectGame() {
		String[] options = new String[2];
		options[0] = new String("Player vs. Player");
		options[1] = new String("Player vs. AI");
		int gameplay = JOptionPane.showOptionDialog(this.getContentPane(), "", "PLAYERS", 0,
				JOptionPane.INFORMATION_MESSAGE, null, options, null);
		if (gameplay == 0) {
			System.out.println("PVP");
		} else if (gameplay == 1) {
			System.out.println("Player vs AI");
			MapEntityLoader.ai = true;

		}
		Application.game();
	}

	public void setup() {
		title();
		options();

	}

	public void title() {
		JLabel title = new JLabel("ADVANCED WARS");
		title.setFont(new Font("Serifs", Font.BOLD, 100));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBackground(Color.white);

		title.setBounds(0, 0, 1080, 100);
		
		JLabel produce = new JLabel("Made by: Apollo Forty Two");
		produce.setFont(new Font("Serifs", Font.PLAIN, 10));
		produce.setHorizontalAlignment(SwingConstants.CENTER);
		produce.setBackground(Color.white);
		produce.setBounds(0, 600, 1000, 100);
		
		this.add(produce);
		
		this.add(title);

	}
}

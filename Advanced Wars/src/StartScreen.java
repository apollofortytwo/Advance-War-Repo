import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Start Screen:
 * 		Gives the user the ability to start a new game or load a game
 * 
 * Map selection Screen:
 * 		picks the map the user will play
 * @author Bryan
 *
 */
public class StartScreen extends JFrame implements ActionListener {

	public StartScreen() {
		this.setSize(1080, 720);
		this.setLayout(null);
		setup();
		this.setVisible(true);
	}
	
	public void setup(){
		title();
		options();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("NEW")) {
			mapSelectionScreen();
		} else {
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("ADVANCED WARS SAVE FILE", "save", "save");
			fc.setFileFilter(filter);
			fc.showOpenDialog(this);
			if(fc.getSelectedFile() != null){
				LoadGame.file = fc.getSelectedFile();
				try {
					new LoadGame();
				} catch (Exception e) {
				}
			}else{
				return;
			}

		}

	}

	private void mapSelectionScreen() {
		this.getContentPane().removeAll();
		this.getContentPane().repaint();

		this.setLayout(new GridLayout(2, 0));

		JButton mobaMap = new JButton("MOBA");
		mobaMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MapLoader.setMap("MOBA");
				Application.game();
				return;
			}

		});
		JButton large = new JButton("Large");
		large.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MapLoader.setMap("Large");
				Application.size = new double[][] {
						{ 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32,
								32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 }, // Columns
						{ 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 } // Rows;
				};
				;
				Application.setTable();
				Terrain.terrainArray = new Terrain[Application.size[0].length][Application.size[1].length];
				Application.game();
				return;
			}

		});

		this.add(mobaMap);
		this.add(large);

		this.revalidate();
		this.getContentPane().repaint();
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

	public void title() {
		JLabel title = new JLabel("ADVANCED WARS");
		title.setFont(new Font("Serifs", Font.BOLD, 100));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBackground(Color.white);

		title.setBounds(0, 0, 1080, 100);
		this.add(title);

	}
}

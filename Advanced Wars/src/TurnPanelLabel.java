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

	public TurnPanelLabel() {
		setPreferredSize(new Dimension(300, 25));
		setBorder(BorderFactory.createEtchedBorder());
		setOpaque(true);
		setFont(new Font("MS PGothic", Font.BOLD, 27));
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}

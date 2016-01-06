import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TurnPanelLabel extends JLabel {

	public TurnPanelLabel() {
		setPreferredSize(new Dimension(300, 25));
		setBorder(BorderFactory.createEtchedBorder());
		setOpaque(true);
		setFont(new Font("MS PGothic", Font.BOLD, 27));
		setHorizontalAlignment(SwingConstants.CENTER);
	}
}

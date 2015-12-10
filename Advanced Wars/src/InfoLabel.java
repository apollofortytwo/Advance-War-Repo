import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class InfoLabel extends JLabel {
	private String defaultText;
	private int integer = 0;

	public InfoLabel(String text) {
		defaultText = text;
		// this.setPreferredSize(new Dimension(212, 40));
		this.setBorder(BorderFactory.createEtchedBorder(1));
		this.setText(defaultText);
	}

	public void emptyText() {
		this.setText(defaultText);
	}

	public int getInteger() {
		return integer;
	}

	public void setInteger(int integer) {
		this.integer = integer;
	}

	public void updateText(String text) {
		this.setText(defaultText + " " + text);
	}
}

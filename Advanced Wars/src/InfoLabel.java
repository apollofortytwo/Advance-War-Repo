import javax.swing.BorderFactory;
import javax.swing.JLabel;

/*
 * Displays an information in a box
 * Information comes with a default starting text
 * 
 * example;
 * 		health: <-- default
 * 		20 		<-- info
 * 		result:    	health: 20
 * 		30 		<-- new Information
 * 		result 		health: 30
 */
public class InfoLabel extends JLabel {
	private String defaultText;
	private int integer = 0;

	/**
	 * 
	 * @param defaultText
	 *            (The text that will be used as a default)
	 */
	public InfoLabel(String defaultText) {
		this.defaultText = defaultText;
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

import javax.swing.JOptionPane;

public class WinScreen {

	static boolean hasWon() {
		int blueAlive = 0;
		int redAlive = 0;
		for (Unit unit : Unit.UnitsArray) {
			if (unit.getTeam().equals("Blue")) {
				blueAlive++;
			} else {
				redAlive++;
			}
		}
		if (blueAlive == 0) {
			new WinScreen("Red");
			return true;
		} else if (redAlive == 0) {
			new WinScreen("Blue");
			return true;
		} else {
			return false;
		}
	}

	WinScreen(String winner) {
		System.out.println(winner + " has Won!");
		JOptionPane.showMessageDialog(Interface.mainFrame,
				winner + " HAS WON!", "WINNER!", JOptionPane.PLAIN_MESSAGE);
	}
}

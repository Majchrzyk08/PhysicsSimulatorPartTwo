package simulator.view;

import java.awt.BorderLayout;

import javax.swing.*;

import simulator.control.Controller;

public class MainWindow extends JFrame {

	private Controller _ctrl;
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
private void initGUI() {
	JPanel mainPanel = new JPanel(new BorderLayout());
	setContentPane(mainPanel);
	
}

}
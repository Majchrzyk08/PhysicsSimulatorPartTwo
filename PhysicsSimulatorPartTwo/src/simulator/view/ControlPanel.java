package simulator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver, ActionListener {

	// ...
	private Controller _ctrl;
	private boolean _stopped;
	JButton open = new JButton();
	JButton force = new JButton();
	JButton run = new JButton();
	JButton stop = new JButton();
	JButton exit = new JButton();
	JPanel panel = new JPanel();
	JSpinner spinner = new JSpinner();

	ControlPanel(Controller ctrl) {
	_ctrl = ctrl;
	_stopped = true;
	initGUI();
	_ctrl.addObserver(this);
	}
	
	private void initGUI() {
	
	open.addActionListener(this);
	force.addActionListener(this);
	run.addActionListener(this);
	stop.addActionListener(this);
	exit.addActionListener(this);
	
	open.setIcon(new ImageIcon("resources/icons/open.png"));
	force.setIcon(new ImageIcon("resources/icons/physics.png"));
	run.setIcon(new ImageIcon("resources/icons/run.png"));
	stop.setIcon(new ImageIcon("resources/icons/stop.png"));
	exit.setIcon(new ImageIcon("resources/icons/exit.png"));
	
	panel.add(open);
	panel.add(force);
	panel.add(run);
	panel.add(stop);
	panel.add(exit);
	
	
	// TODO build the tool bar by adding buttons, etc.
	}
	
	// other private/protected methods
	// ...
	
	private void run_sim(int n) {
		if ( n>0 && !_stopped ) {
		try {
		_ctrl.run(1);
		} catch (Exception e) {
		// TODO show the error in a dialog box
		// TODO enable all buttons
		_stopped = true;
		return;
		}
		SwingUtilities.invokeLater( new Runnable() {
		@Override
		public void run() {
		run_sim(n-1);
		}
		});
		} else {
		_stopped = true;
		// TODO enable all buttons
		}
		}
	
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}

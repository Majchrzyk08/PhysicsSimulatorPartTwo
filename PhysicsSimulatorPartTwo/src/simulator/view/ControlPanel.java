package simulator.view;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;


public class ControlPanel extends JPanel implements SimulatorObserver, ActionListener {

	// ...
	private Controller _ctrl;
	private boolean _stopped;
	
	private JLabel step;
	private JLabel deltaT;
	private JButton open;
	private JButton force;
	private JButton run;
	private JButton stop;
	private JButton exit;
	private JSpinner stepText;
	private JTextField time;
	
	ControlPanel(Controller ctrl) {
	_ctrl = ctrl;
	_stopped = true;
	initGUI();
	_ctrl.addObserver(this);
	}
	
	private void initGUI() {
	
	open = new JButton();
	force = new JButton();
	run = new JButton();
	stop = new JButton();
	exit = new JButton();
	stepText = new JSpinner();
	time = new JTextField("2500");
	step = new JLabel("Steps: ");
	deltaT = new JLabel("Delta-time: ");
	                        
	stepText.setValue(5000);
	
	open.setIcon(new ImageIcon("resources/icons/open.png"));
	force.setIcon(new ImageIcon("resources/icons/physics.png"));
	run.setIcon(new ImageIcon("resources/icons/run.png"));
	stop.setIcon(new ImageIcon("resources/icons/stop.png"));
	exit.setIcon(new ImageIcon("resources/icons/exit.png"));
	
	this.add(open);
	this.add(force);
	this.add(run);
	this.add(stop);
	this.add(step);
	this.add(stepText);
	this.add(deltaT);
	this.add(time);
	this.add(exit);

	
	open.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			File file;
			JFileChooser fc = new JFileChooser();
			int value = fc.showOpenDialog(open);
			
			if(value == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				System.out.println("Loading: " + file.getName());
				} 
			else {
				 file = null;
				}
			
			if(file != null) {
				_ctrl.reset();
				InputStream iFile;
				try {
					iFile = new FileInputStream(file);
					_ctrl.loadBodies(iFile);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	});
	
	force.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	
	run.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			open.setEnabled(false);
			force.setEnabled(false);
			exit.setEnabled(false);
			
			_stopped = false;
			
			run_sim((Integer)stepText.getValue());

		}
	});
	
	stop.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			_stopped = true;
			
		}
	});
	
	exit.addActionListener(new ActionListener(){	
		@Override
		public void actionPerformed(ActionEvent e) {
			int input = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Select and option...", JOptionPane.YES_NO_OPTION);
			
			System.out.println(input);
			
			if(input == 0) {
				System.exit(0);
			}
			
		}
	});

}
	// other private/protected methods
	// ...
	
	private void run_sim(int n) {
		if ( n>0 && !_stopped ) {
		try {
		_ctrl.run(1);
		} catch (Exception e) {
		// TODO show the error in a dialog box
			
		JOptionPane.showMessageDialog(null, e.getMessage(), "error", Dialog.ERROR);
		
		// TODO enable all buttons
		open.setEnabled(true);
		force.setEnabled(true);
		exit.setEnabled(true);
		
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
		open.setEnabled(true);
		force.setEnabled(true);
		exit.setEnabled(true);
		}
		}
	
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		this.time.setText(String.valueOf(dt));

	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		this.time.setText(String.valueOf(dt));

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
		this.time.setText(String.valueOf(dt));

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

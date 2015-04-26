package cse5321;

import java.awt.EventQueue;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.Aircraft;
import control.Aircraft.CONTROL;
import control.Aircraft.STATUS;


@SuppressWarnings("serial")
public class Display extends JFrame {
	
	private class Ticker extends TimerTask{
		
		@Override
		public void run() {
			updateInfo();
		}
		
	}
	
	/**
	 * Private inner class to handle throttle inputs from pilot
	 * WARNING: If there is a "Repeat keystroke" feature on your
	 * computer be sure to disable it, otherwise this will not work
	 * as intended.
	 * @author Matthew Waller
	 *
	 */
	private class AircraftController implements KeyEventDispatcher{

		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			int vkc = e.getKeyCode();
			switch(e.getID()){
			case KeyEvent.KEY_PRESSED:
				examineKeyCode(true, vkc);
				return true;
			case KeyEvent.KEY_RELEASED:
				examineKeyCode(false, vkc);
				return true;
			}
			return false;
		}
		
		private void examineKeyCode(boolean state, int keyCode){
			switch(keyCode){
			case KeyEvent.VK_PLUS:
			case KeyEvent.VK_EQUALS:
				inc_b_down = state;
				return;
			case KeyEvent.VK_UNDERSCORE:
			case KeyEvent.VK_MINUS:
				dec_b_down = state;
				return;
			case KeyEvent.VK_UP:
				up_pressed = state;
				return;
			case KeyEvent.VK_DOWN:
				down_pressed = state;
			}
		}
		
	}

	private JPanel contentPane;
	private JTextField p_Altitude;
	private JTextField p_Ttl;
	private JTextField p_GearPosition;
	private JTextField p_Speed;
	private JTextField p_Alarm;
	private JTextField p_Warning;
	private JTextField c_Altitude;
	private JTextField c_Ttl;
	private JTextField c_GearPosition;
	private JTextField c_Speed;
	private JTextField c_Alarm;
	private JTextField c_Warning;
	private Aircraft aircraft;
	private Ticker ticker;
	private Timer tick_timer;
	private volatile boolean dec_b_down = false;
	private volatile boolean inc_b_down = false;
	private volatile boolean up_pressed = false;
	private volatile boolean down_pressed = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Display() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 473);
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new AircraftController());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PILOT");
		lblNewLabel.setBounds(42, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" Co - PILOT");
		lblNewLabel_1.setBounds(489, 11, 95, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel P_Altitude = new JLabel("Altitude");
		P_Altitude.setBounds(10, 101, 66, 14);
		contentPane.add(P_Altitude);
		
		JLabel lblNewLabel_3 = new JLabel("Altitude");
		lblNewLabel_3.setBounds(406, 101, 101, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Time to Landing");
		lblNewLabel_4.setBounds(10, 144, 113, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel gear_position = new JLabel("Gear Position");
		gear_position.setBounds(10, 189, 113, 14);
		contentPane.add(gear_position);
		
		JLabel lblNewLabel_6 = new JLabel("Speed");
		lblNewLabel_6.setBounds(10, 233, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Alarm");
		lblNewLabel_7.setBounds(10, 275, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Warning");
		lblNewLabel_8.setBounds(10, 335, 113, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Time to Landing");
		lblNewLabel_9.setBounds(406, 144, 133, 14);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Gear Position");
		lblNewLabel_10.setBounds(406, 189, 133, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Speed");
		lblNewLabel_11.setBounds(406, 233, 101, 14);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Alarm");
		lblNewLabel_12.setBounds(406, 275, 101, 14);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Warning");
		lblNewLabel_13.setBounds(406, 335, 101, 14);
		contentPane.add(lblNewLabel_13);
		
		JButton Alarm_button = new JButton("Silence Aural Alarm");
		Alarm_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				aircraft.silence_AS_Alarm();
				aircraft.silence_GND_Alarm();
			}
		});
		Alarm_button.setBounds(42, 381, 133, 23);
		contentPane.add(Alarm_button);
		
		p_Altitude = new JTextField();
		p_Altitude.setEditable(false);
		p_Altitude.setBounds(152, 97, 86, 20);
		contentPane.add(p_Altitude);
		p_Altitude.setColumns(10);
		
		p_Ttl = new JTextField();
		p_Ttl.setEditable(false);
		p_Ttl.setBounds(152, 140, 86, 20);
		contentPane.add(p_Ttl);
		p_Ttl.setColumns(10);
		
		p_GearPosition = new JTextField();
		p_GearPosition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggle_gear();
			}
		});
		p_GearPosition.setEditable(false);
		p_GearPosition.setBounds(152, 185, 86, 20);
		contentPane.add(p_GearPosition);
		p_GearPosition.setColumns(10);
		
		p_Speed = new JTextField();
		p_Speed.setEditable(false);
		p_Speed.setBounds(152, 229, 86, 20);
		contentPane.add(p_Speed);
		p_Speed.setColumns(10);
		
		p_Alarm = new JTextField();
		p_Alarm.setEditable(false);
		p_Alarm.setBounds(152, 271, 236, 35);
		contentPane.add(p_Alarm);
		p_Alarm.setColumns(10);
		
		p_Warning = new JTextField();
		p_Warning.setEditable(false);
		p_Warning.setBounds(152, 326, 236, 43);
		contentPane.add(p_Warning);
		p_Warning.setColumns(10);
		
		c_Altitude = new JTextField();
		c_Altitude.setEditable(false);
		c_Altitude.setBounds(574, 98, 86, 20);
		contentPane.add(c_Altitude);
		c_Altitude.setColumns(10);
		
		c_Ttl = new JTextField();
		c_Ttl.setEditable(false);
		c_Ttl.setBounds(574, 141, 86, 20);
		contentPane.add(c_Ttl);
		c_Ttl.setColumns(10);
		
		c_GearPosition = new JTextField();
		c_GearPosition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggle_gear();
			}
		});
		c_GearPosition.setEditable(false);
		c_GearPosition.setBounds(574, 186, 86, 20);
		contentPane.add(c_GearPosition);
		c_GearPosition.setColumns(10);
		
		c_Speed = new JTextField();
		c_Speed.setEditable(false);
		c_Speed.setBounds(574, 230, 86, 20);
		contentPane.add(c_Speed);
		c_Speed.setColumns(10);
		
		c_Alarm = new JTextField();
		c_Alarm.setEditable(false);
		c_Alarm.setBounds(574, 269, 189, 38);
		contentPane.add(c_Alarm);
		c_Alarm.setColumns(10);
		
		c_Warning = new JTextField();
		c_Warning.setEditable(false);
		c_Warning.setBounds(574, 332, 189, 38);
		contentPane.add(c_Warning);
		c_Warning.setColumns(10);
		
		ticker = new Ticker();
		tick_timer = new Timer(true);
		tick_timer.scheduleAtFixedRate(ticker, 2000, 1000);
		
		aircraft = new Aircraft();
		paintDisplay();
	}
	
	private void toggle_gear(){
		boolean status = aircraft.toggle_gear();
		if(status){
			p_GearPosition.setText("DOWN");
			c_GearPosition.setText("DOWN");
		}else{
			p_GearPosition.setText("UP");
			c_GearPosition.setText("UP");
		}
	}
	private void paintDisplay(){
		String info;
		if(up_pressed && !down_pressed)
			aircraft.set_elevon(CONTROL.UP);
		else if(down_pressed && !up_pressed)
			aircraft.set_elevon(CONTROL.DOWN);
		else
			aircraft.set_elevon(CONTROL.NONE);
		info = Integer.toString(aircraft.get_altitude());
		p_Altitude.setText(info);
		c_Altitude.setText(info);
		info = Integer.toString(aircraft.get_time());
		p_Ttl.setText(info);
		c_Ttl.setText(info);
		if(inc_b_down && !dec_b_down)
			aircraft.set_throttle(CONTROL.UP);
		else if(dec_b_down && !inc_b_down)
			aircraft.set_throttle(CONTROL.DOWN);
		else
			aircraft.set_throttle(CONTROL.NONE);
		info = Integer.toString(aircraft.get_speed());
		p_Speed.setText(info);
		c_Speed.setText(info);
		boolean b_info = aircraft.is_gear_down();
		if(b_info){
			p_GearPosition.setText("DOWN");
			c_GearPosition.setText("DOWN");
		}else{
			p_GearPosition.setText("UP");
			c_GearPosition.setText("UP");
		}
		Aircraft.STATUS g_status = aircraft.get_GND_Status();
		Aircraft.STATUS a_status = aircraft.get_AS_Status();
		info = "";
		info += (g_status == STATUS.ALARM) ? "GEAR NOT DOWN" : "";
		info += (a_status == STATUS.ALARM) ? "AIR SPEED" : "";
		p_Alarm.setText(info);
		c_Alarm.setText(info);
		info = "";
		info += (g_status == STATUS.WARNING) ? "GEAR NOT DOWN" : "";
		info += (a_status == STATUS.WARNING) ? "AIR SPEED" : "";
		info += (aircraft.is_airbrake()) ? "AIRBRAKE" : "";
		info += (aircraft.is_override()) ? "OVERRIDE" : "";
		p_Warning.setText(info);
		c_Warning.setText(info);
	}
	
	public void updateInfo(){
		aircraft.tick();
		paintDisplay();
	}
}

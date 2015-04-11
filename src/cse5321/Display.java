package cse5321;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Display extends JFrame {

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
		setBounds(100, 100, 722, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PILOT");
		lblNewLabel.setBounds(42, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" Co - PILOT");
		lblNewLabel_1.setBounds(489, 11, 66, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel P_Altitude = new JLabel("Altitude");
		P_Altitude.setBounds(10, 101, 46, 14);
		contentPane.add(P_Altitude);
		
		JLabel lblNewLabel_3 = new JLabel("Altitude");
		lblNewLabel_3.setBounds(394, 101, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Time to Landing");
		lblNewLabel_4.setBounds(10, 144, 78, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel gear_position = new JLabel("Gear Position");
		gear_position.setBounds(10, 189, 78, 14);
		contentPane.add(gear_position);
		
		JLabel lblNewLabel_6 = new JLabel("Speed");
		lblNewLabel_6.setBounds(10, 233, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Alarm");
		lblNewLabel_7.setBounds(10, 275, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Warning");
		lblNewLabel_8.setBounds(10, 335, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Time to Landing");
		lblNewLabel_9.setBounds(394, 144, 78, 14);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Gear Position");
		lblNewLabel_10.setBounds(394, 189, 78, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Speed");
		lblNewLabel_11.setBounds(394, 233, 46, 14);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Alarm");
		lblNewLabel_12.setBounds(394, 275, 46, 14);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Warning");
		lblNewLabel_13.setBounds(394, 335, 46, 14);
		contentPane.add(lblNewLabel_13);
		
		JButton Alarm_button = new JButton("Silence Aural Alarm");
		Alarm_button.setBounds(42, 381, 133, 23);
		contentPane.add(Alarm_button);
		
		p_Altitude = new JTextField();
		p_Altitude.setBounds(118, 98, 86, 20);
		contentPane.add(p_Altitude);
		p_Altitude.setColumns(10);
		
		p_Ttl = new JTextField();
		p_Ttl.setBounds(118, 141, 86, 20);
		contentPane.add(p_Ttl);
		p_Ttl.setColumns(10);
		
		p_GearPosition = new JTextField();
		p_GearPosition.setBounds(118, 186, 86, 20);
		contentPane.add(p_GearPosition);
		p_GearPosition.setColumns(10);
		
		p_Speed = new JTextField();
		p_Speed.setBounds(118, 230, 86, 20);
		contentPane.add(p_Speed);
		p_Speed.setColumns(10);
		
		p_Alarm = new JTextField();
		p_Alarm.setBounds(118, 272, 236, 35);
		contentPane.add(p_Alarm);
		p_Alarm.setColumns(10);
		
		p_Warning = new JTextField();
		p_Warning.setBounds(118, 327, 236, 43);
		contentPane.add(p_Warning);
		p_Warning.setColumns(10);
		
		c_Altitude = new JTextField();
		c_Altitude.setBounds(507, 98, 86, 20);
		contentPane.add(c_Altitude);
		c_Altitude.setColumns(10);
		
		c_Ttl = new JTextField();
		c_Ttl.setBounds(507, 141, 86, 20);
		contentPane.add(c_Ttl);
		c_Ttl.setColumns(10);
		
		c_GearPosition = new JTextField();
		c_GearPosition.setBounds(507, 186, 86, 20);
		contentPane.add(c_GearPosition);
		c_GearPosition.setColumns(10);
		
		c_Speed = new JTextField();
		c_Speed.setBounds(507, 230, 86, 20);
		contentPane.add(c_Speed);
		c_Speed.setColumns(10);
		
		c_Alarm = new JTextField();
		c_Alarm.setBounds(507, 269, 189, 38);
		contentPane.add(c_Alarm);
		c_Alarm.setColumns(10);
		
		c_Warning = new JTextField();
		c_Warning.setBounds(507, 332, 189, 38);
		contentPane.add(c_Warning);
		c_Warning.setColumns(10);
	}
}

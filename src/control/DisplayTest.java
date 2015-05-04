package control;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import cse5321.Display;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

/**
 * This class is used to test the Display Controller Class
 * @author Matthew Waller
 * @see  Display
 *
 */
@SuppressWarnings("serial")
public class DisplayTest extends Display implements ActionListener{
	private JButton Scenario_1;
	private JButton Scenario_2;
	private JButton Scenario_3;
	private JButton Scenario_4;
	private JButton Scenario_5;
	private JButton Scenario_6;
	private JButton Scenario_7;
	private JButton Scenario_8;
	private AircraftTest harness;
	private JTextField txtJunitResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayTest frame = new DisplayTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public DisplayTest() throws Exception {
		stop_Simulation();
		setBounds(100, 100, 805, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Scenario_1 = new JButton("Scenario 1");
		Scenario_1.setBounds(23, 441, 117, 25);
		getContentPane().add(Scenario_1);
		Scenario_1.addActionListener(this);
		
		Scenario_2 = new JButton("Scenario 2");
		Scenario_2.setBounds(152, 441, 117, 25);
		getContentPane().add(Scenario_2);
		Scenario_2.addActionListener(this);
		
		Scenario_3 = new JButton("Scenario 3");
		Scenario_3.setBounds(281, 441, 117, 25);
		getContentPane().add(Scenario_3);
		Scenario_3.addActionListener(this);
		
		Scenario_4 = new JButton("Scenario  4");
		Scenario_4.setBounds(410, 441, 117, 25);
		getContentPane().add(Scenario_4);
		Scenario_4.addActionListener(this);
		
		Scenario_5 = new JButton("Scenario 5");
		Scenario_5.setBounds(23, 478, 117, 25);
		getContentPane().add(Scenario_5);
		Scenario_5.addActionListener(this);
		
		Scenario_6 = new JButton("Scenario 6");
		Scenario_6.setBounds(152, 478, 117, 25);
		getContentPane().add(Scenario_6);
		Scenario_6.addActionListener(this);
		
		Scenario_7 = new JButton("Scenario 7");
		Scenario_7.setBounds(281, 478, 117, 25);
		getContentPane().add(Scenario_7);
		Scenario_7.addActionListener(this);
		
		Scenario_8 = new JButton("Scenario 8");
		Scenario_8.setBounds(410, 478, 117, 25);
		getContentPane().add(Scenario_8);
		Scenario_8.addActionListener(this);
		
		txtJunitResult = new JTextField();
		txtJunitResult.setFont(new Font("Dialog", Font.BOLD, 20));
		txtJunitResult.setEditable(false);
		txtJunitResult.setBounds(539, 444, 254, 59);
		getContentPane().add(txtJunitResult);
		txtJunitResult.setColumns(10);
		harness = new AircraftTest();
		AircraftTest.setUpBeforeClass();
		harness.set_aircraft(get_Aircraft());
	}
	
	protected JTextField getTxtJunitResult() {
		return txtJunitResult;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton) e.getSource();
		try{
			if(source == Scenario_1)
				harness.bv_test_2();
			else if(source == Scenario_2)
				harness.bv_test_36();
			else if(source == Scenario_3)
				harness.bv_test_5();
			else if(source == Scenario_4)
				harness.bv_test_10();
			else if(source == Scenario_5)
				harness.bv_test_20();
			else if(source == Scenario_6)
				harness.bv_test_22();
			else if(source == Scenario_7)
				harness.bv_test_32();
			else if(source == Scenario_8)
				harness.bv_test_33();
			txtJunitResult.setBackground(Color.GREEN);
			txtJunitResult.setText("PASS!");
		}catch(AssertionError err){
			txtJunitResult.setBackground(Color.RED);
			txtJunitResult.setText("FAILED!");
		}
		paintDisplay();
	}
}

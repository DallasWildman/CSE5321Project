package control;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import control.Aircraft.STATUS;

public class AircraftTest {
	
	private static class OutputScenario{
		STATUS gear;
		STATUS airspeed;
		boolean airbrake;
		boolean override;
		public OutputScenario(int number){
			setScenario(number);
		}
		public void setScenario(int number){
			if(number < 1 || number > 8)
				return;
			gear = (number == 1 || number == 3) ? STATUS.ALARM : STATUS.NONE;
			airspeed = (number >= 5) ? STATUS.ALARM : STATUS.NONE;
			airbrake = ((number >= 3 && number <= 5) || number == 7);
			override = (number >= 7);
		}
	}

	
	protected static Aircraft subject;
	private static OutputScenario checksum;
	
	protected STATUS get_gear(){
		return checksum.gear;
	}
	
	protected STATUS get_airspeed(){
		return checksum.airspeed;
	}
	
	protected boolean get_airbrake(){
		return checksum.airbrake;
	}
	
	protected boolean get_override(){
		return checksum.override;
	}

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		subject = new Aircraft();
		checksum = new OutputScenario(2);
	}
	
	public void run_test(int scen, int speed, int time, boolean gear){
		subject.set_speed(speed);
		subject.set_time(time);
		if(subject.is_gear_down() != gear)
			subject.toggle_gear();
		checksum.setScenario(scen);
		subject.calculate_state();
		assertEquals(subject.get_GND_Status(), checksum.gear);
		assertEquals(subject.get_AS_Status(), checksum.airspeed);
		assertEquals(subject.is_airbrake(), checksum.airbrake);
		assertEquals(subject.is_override(), checksum.override);
	}

	@Test
	public void bv_test_1(){
		run_test(1, 249, 120, false);
	}
	
	@Test
	public void bv_test_2(){
		subject.set_altitude(999);
		run_test(1, 249, 120, false);
	}
	
	@Test
	public void bv_test_3(){
		subject.set_altitude(999);
		run_test(1,300,120,false);
	}
	
	@Test
	public void bv_test_4(){
		run_test(3,250,59,false);
	}
	
	@Test
	public void bv_test_5(){
		run_test(3,300,59,false);
	}
	
	@Test
	public void bv_test_6(){
		run_test(1,250,60,false);
	}
	
	@Test
	public void bv_test_7(){
		run_test(1,300,60,false);
	}
	
	@Test
	public void bv_test_8(){
		run_test(1,250,120,false);
	}
	
	@Test
	public void bv_test_9(){
		run_test(1,300,120,false);
	}
	
	@Test
	public void bv_test_10(){
		run_test(4,250,59,true);
	}
	
	@Test
	public void bv_test_11(){
		run_test(4,300,59,true);
	}
	
	@Test
	public void bv_test_12(){
		run_test(3,301,59,false);
	}
	
	@Test
	public void bv_test_13(){
		run_test(3,400,59,false);
	}
	
	@Test
	public void bv_test_14(){
		run_test(1,301,60,false);
	}
	
	@Test
	public void bv_test_15(){
		run_test(1,400,60,false);
	}
	
	@Test
	public void bv_test_16(){
		run_test(1,301,120,false);
	}
	
	@Test
	public void bv_test_17(){
		run_test(1,400,120,false);
	}
	
	@Test
	public void bv_test_18(){
		subject.set_altitude(999);
		run_test(1,301,121,false);
	}
	
	@Test
	public void bv_test_19(){
		subject.set_altitude(999);
		run_test(1,400,121,false);
	}
	
	@Test
	public void bv_test_20(){
		run_test(5,301,59,true);
	}
	
	@Test
	public void bv_test_21(){
		run_test(5,400,59,true);
	}
	
	@Test
	public void bv_test_22(){
		run_test(6,301,60,true);
	}
	
	@Test
	public void bv_test_23(){
		run_test(6,400,60,true);
	}
	
	@Test
	public void bv_test_24(){
		run_test(6,301,120,true);
	}
	
	@Test
	public void bv_test_25(){
		run_test(6,400,120,true);
	}
	
	@Test
	public void bv_test_26(){
		run_test(6,301,121,true);
	}
	
	@Test
	public void bv_test_27(){
		run_test(6,400,121,true);
	}
	
	@Test
	public void bv_test_28(){
		run_test(3,401,59,false);
	}
	
	@Test
	public void bv_test_29(){
		run_test(1,401,60,false);
	}
	
	@Test
	public void bv_test_30(){
		run_test(1,401,120,false);
	}
	
	@Test
	public void bv_test_31(){
		subject.set_altitude(999);
		run_test(1,401,121,false);
	}
	
	@Test
	public void bv_test_32(){
		run_test(7,401,59,true);
	}
	
	@Test
	public void bv_test_33(){
		run_test(8,401,60,true);
	}
	
	@Test
	public void bv_test_34(){
		run_test(8,401,120,true);
	}
	
	@Test
	public void bv_test_35(){
		run_test(8,401,121,true);
	}
	
	@Test
	public void bv_test_36(){
		subject.set_altitude(1000);
		run_test(2,301,121,false);
	}
	
	@Test
	public void bv_test_37(){
		run_test(2,249,120,true);
	}
	
	@Test
	public void dc_test_1(){
		run_test(2,249,59,true);
	}
}
